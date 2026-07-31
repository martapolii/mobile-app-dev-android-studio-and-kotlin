package com.example.martapolishchuk_comp304_401_lab03_exercise02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.navigation.AppNavigation
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens.LocalActivity
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.theme.Martapolishchuk_COMP304_401_Lab03_Exercise02Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Martapolishchuk_COMP304_401_Lab03_Exercise02Theme {
                CompositionLocalProvider(LocalActivity provides this) {
                    // Get window size class for responsive layouts
                    val windowSizeClass = calculateWindowSizeClass(this)
                    // Create navigation controller
                    val navController = rememberNavController()

                    // main app content
                    AppContent(
                        windowSizeClass = windowSizeClass,
                        navController = navController
                    )
                }
            }
        }
    }
} // main activity

@Composable
private fun AppContent(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    navController: androidx.navigation.NavHostController
) {
    MaterialTheme {
        AppNavigation(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
}