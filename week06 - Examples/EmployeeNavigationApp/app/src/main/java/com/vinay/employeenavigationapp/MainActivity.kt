package com.vinay.employeenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.vinay.employeenavigationapp.ui.theme.EmployeeNavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmployeeNavigationAppTheme {
                    val navController = rememberNavController()
                    AppNavigation(navController)
            }
        }
    }
}

