package com.example.midtermcandytemplate.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.model.CandyRepositoryProvider
import com.example.midtermcandytemplate.model.DetailScreenMode
import com.example.midtermcandytemplate.ui.theme.MidtermCandyTemplateTheme
import com.example.midtermcandytemplate.view.components.CandyForm
import com.example.midtermcandytemplate.view.components.CommonTopBar
import com.example.midtermcandytemplate.viewmodel.DetailViewModel
import com.example.midtermcandytemplate.viewmodel.DetailViewModelFactory

/**
 * Second real activity in the template.
 *
 * This receives Intent extras, loads one candy item, and lets you edit it.
 */
class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CandyRepositoryProvider.repository
        val factory = DetailViewModelFactory(repository)
        val detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val candyId = intent.getIntExtra(EXTRA_CANDY_ID, -1)
        val screenMode = readScreenMode()

        setContent {
            MidtermCandyTemplateTheme {
                DetailScreen(
                    candyId = candyId,
                    screenMode = screenMode,
                    detailViewModel = detailViewModel,
                    onBackClick = { finish() },
                    onSaveComplete = { finish() }
                )
            }
        }
    }

    private fun readScreenMode(): DetailScreenMode {
        val rawMode = intent.getStringExtra(EXTRA_SCREEN_MODE) ?: DetailScreenMode.EDIT.name

        return runCatching {
            DetailScreenMode.valueOf(rawMode)
        }.getOrDefault(DetailScreenMode.EDIT)
    }

    companion object {
        const val EXTRA_CANDY_ID = "extra_candy_id"
        const val EXTRA_SCREEN_MODE = "extra_screen_mode"

        /**
         * For midterm speed this template passes the candy id and screen mode.
         *
         * If you ever need the full model instead, you can make Candy Parcelable and
         * place the object into the Intent here.
         */
        fun createIntent(
            context: Context,
            candyId: Int,
            screenMode: DetailScreenMode
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_CANDY_ID, candyId)
                putExtra(EXTRA_SCREEN_MODE, screenMode.name)
            }
        }
    }
}

@Composable
fun DetailScreen(
    candyId: Int,
    screenMode: DetailScreenMode,
    detailViewModel: DetailViewModel,
    onBackClick: () -> Unit,
    onSaveComplete: () -> Unit
) {
    LaunchedEffect(candyId, screenMode) {
        detailViewModel.loadCandy(candyId = candyId, screenMode = screenMode)
    }

    val uiState by detailViewModel.uiState.collectAsState()
    val isReadOnly = uiState.screenMode == DetailScreenMode.VIEW

    Scaffold(
        topBar = {
            CommonTopBar(
                title = when (uiState.screenMode) {
                    DetailScreenMode.VIEW -> "View Candy"
                    DetailScreenMode.EDIT -> "Edit Candy"
                    DetailScreenMode.CREATE -> "Create Candy"
                },
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState()
        ) {
            item {
                // Text
                Text(
                    text = "This screen demonstrates real activity navigation plus editable Compose controls.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Column
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        // Text
                        Text(
                            text = "Received Intent extras",
                            style = MaterialTheme.typography.titleMedium
                        )

                        // Text
                        Text(text = "Candy id: ${uiState.receivedCandyId}")

                        // Text
                        Text(text = "Screen mode: ${uiState.screenMode.name}")
                    }
                }
            }

            item {
                CandyForm(
                    formState = uiState.formState,
                    validationMessage = uiState.validationMessage,
                    readOnly = isReadOnly,
                    primaryButtonText = "Save Changes",
                    showPrimaryButton = !isReadOnly,
                    onNameChange = detailViewModel::onNameChange,
                    onTypeChange = detailViewModel::onTypeChange,
                    onSugarFreeChange = detailViewModel::onSugarFreeChange,
                    onFlavorProfileChange = detailViewModel::onFlavorProfileChange,
                    onDescriptionChange = detailViewModel::onDescriptionChange,
                    onFavoriteToggle = detailViewModel::onFavoriteToggle,
                    onPrimaryButtonClick = {
                        if (detailViewModel.saveCandy()) {
                            onSaveComplete()
                        }
                    }
                )
            }

            item {
                // Optional Material 3 shelf.
                // Uncomment the next line when you want extra component samples on screen.
                // OptionalStudyComponents()
            }
        }
    }
}
