// 301432299 - Marta Polishchuk
package com.example.martapolishchuk_comp304_midterm.view


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_midterm.R
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

        setContent {
            MartaPolishchuk_COMP304_MidtermTheme {
                HomeScreen(
                    onOpenInventoryClick = {
                        startActivity(CarInventoryDisplayActivity.createIntent(this))
                    }
                )
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
    onOpenInventoryClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Application logo",
                modifier = Modifier.size(180.dp)
            )

            IconButton(
                onClick = onOpenInventoryClick,
                modifier = Modifier.size(96.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Open car inventory",
                    modifier = Modifier.size(72.dp)
                )
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
