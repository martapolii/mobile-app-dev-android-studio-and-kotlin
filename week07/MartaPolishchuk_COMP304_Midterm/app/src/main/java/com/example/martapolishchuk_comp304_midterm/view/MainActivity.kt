// 301432299 - Marta Polishchuk
package com.example.martapolishchuk_comp304_midterm.view


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_midterm.ui.theme.MartaPolishchuk_COMP304_MidtermTheme
/*
Main Activity for 'UsedCarSales' App
-Display the application logo and image button (maybe a vehicle-themed).
-When the user taps the image button, navigate to the Car Entry Screen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = CarRepositoryProvider.repository
        val factory = MainActivityViewModelFactory(repository)
        val homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)(factory)

        setContent {
            MartaPolishchuk_COMP304_MidtermTheme {
                HomeScreen(
                    homeViewModel = homeViewModel)
            }
        }
    }
}

// these will be used for navigating between screens
enum class Screen{
    HOME,
    VIEW,
    CREATE
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
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MartaPolishchuk_COMP304_MidtermTheme {
//        Greeting("Android")
//    }
//}