package com.example.midtermcandytemplate.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.R
import com.example.midtermcandytemplate.model.CandyRepositoryProvider
import com.example.midtermcandytemplate.model.DetailScreenMode
import com.example.midtermcandytemplate.ui.theme.MidtermCandyTemplateTheme
import com.example.midtermcandytemplate.view.components.CandyListItem
import com.example.midtermcandytemplate.view.components.CommonTopBar
import com.example.midtermcandytemplate.viewmodel.HomeViewModel
import com.example.midtermcandytemplate.viewmodel.HomeViewModelFactory

/**
 * Launcher activity for this template.
 *
 * This activity shows the reusable list layout and opens the detail activity using
 * a real Intent with extras.
 */
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CandyRepositoryProvider.repository
        val factory = HomeViewModelFactory(repository)
        val homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        setContent {
            MidtermCandyTemplateTheme {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    onCandyClick = { candyId ->
                        startActivity(
                            DetailActivity.createIntent(
                                context = this,
                                candyId = candyId,
                                screenMode = DetailScreenMode.EDIT
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onCandyClick: (Int) -> Unit
) {
    val candyList by homeViewModel.candies.collectAsState()

    Scaffold(
        topBar = {
            CommonTopBar(title = "Candy Shelf")
        }
    ) { innerPadding ->
        // Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(16.dp)
            ) {
                // Column
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Text
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )

                    // Text
                    Text(
                        text = "Tap any candy card to open DetailActivity with a real Intent extra.",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // Text
                    Text(
                        text = "The optional CreateCandyActivity exists in the project, but it is intentionally not linked here yet.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Text
                Text(
                    text = "Items: ${candyList.size}",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )

                // Text
                Text(
                    text = "Layout: Box + Column + Row + LazyColumn",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Text
            Text(
                text = "Candy list",
                style = MaterialTheme.typography.titleMedium
            )

            // Lazy column
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = candyList,
                    key = { candy -> candy.id }
                ) { candy ->
                    CandyListItem(
                        candy = candy,
                        onClick = {
                            onCandyClick(candy.id)
                        }
                    )
                }
            }

            // Optional third activity hook.
            // Uncomment and wire this later if you want HomeActivity to open CreateCandyActivity.
            // val context = LocalContext.current
            // Button(onClick = { context.startActivity(CreateCandyActivity.createIntent(context)) }) {
            //     Text("Open Create Template")
            // }
        }
    }
}
