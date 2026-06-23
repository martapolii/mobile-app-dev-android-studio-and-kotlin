package com.example.midtermcandytemplate.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.model.CandyRepositoryProvider
import com.example.midtermcandytemplate.ui.theme.MidtermCandyTemplateTheme
import com.example.midtermcandytemplate.view.components.CandyForm
import com.example.midtermcandytemplate.view.components.CommonTopBar
import com.example.midtermcandytemplate.viewmodel.CreateCandyViewModel
import com.example.midtermcandytemplate.viewmodel.CreateCandyViewModelFactory

/**
 * Optional third activity scaffold
 *
 * compiles as a real activity, but is not linked from HomeActivity
 */
class CreateCandyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CandyRepositoryProvider.repository
        val factory = CreateCandyViewModelFactory(repository)
        val createCandyViewModel = ViewModelProvider(this, factory)[CreateCandyViewModel::class.java]

        setContent {
            MidtermCandyTemplateTheme {
                CreateCandyScreen(
                    createCandyViewModel = createCandyViewModel,
                    onBackClick = { finish() },
                    onSaveComplete = { finish() }
                )
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CreateCandyActivity::class.java)
        }
    }
}

@Composable
fun CreateCandyScreen(
    createCandyViewModel: CreateCandyViewModel,
    onBackClick: () -> Unit,
    onSaveComplete: () -> Unit
) {
    val uiState by createCandyViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Create Candy",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Text
                Text(
                    text = "This activity is intentionally not linked from HomeActivity yet.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item {
                CandyForm(
                    formState = uiState.formState,
                    validationMessage = uiState.validationMessage,
                    readOnly = false,
                    primaryButtonText = "Add Candy",
                    showPrimaryButton = true,
                    onNameChange = createCandyViewModel::onNameChange,
                    onTypeChange = createCandyViewModel::onTypeChange,
                    onSugarFreeChange = createCandyViewModel::onSugarFreeChange,
                    onFlavorProfileChange = createCandyViewModel::onFlavorProfileChange,
                    onDescriptionChange = createCandyViewModel::onDescriptionChange,
                    onFavoriteToggle = createCandyViewModel::onFavoriteToggle,
                    onPrimaryButtonClick = {
                        if (createCandyViewModel.saveCandy()) {
                            onSaveComplete()
                        }
                    }
                )
            }
        }
    }
}
