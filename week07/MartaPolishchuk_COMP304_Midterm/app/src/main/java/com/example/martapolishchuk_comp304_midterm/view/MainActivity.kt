// 301432299 - Marta Polishchuk
package com.example.martapolishchuk_comp304_midterm.view


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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