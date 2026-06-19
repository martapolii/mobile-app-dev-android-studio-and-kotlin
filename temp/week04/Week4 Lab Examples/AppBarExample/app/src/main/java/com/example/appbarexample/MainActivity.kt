package com.example.appbarexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.appbarexample.ui.theme.AppBarExampleTheme
import com.google.androidgamesdk.gametextinput.Settings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
               MyApp()
        }
    }
}

enum class BottomMenuItem {
    Home,
    Profile,
    Settings
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {

    var selectedItem by remember {
        mutableStateOf(BottomMenuItem.Home)
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Jetpack Compose Demo")
                }
            )
        },

        bottomBar = {
            BottomAppBar {

                NavigationBarItem(
                    selected = selectedItem == BottomMenuItem.Home,
                    onClick = {
                        selectedItem = BottomMenuItem.Home

                        Toast.makeText(
                            context,
                            "Home clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == BottomMenuItem.Profile,
                    onClick = {
                        selectedItem = BottomMenuItem.Profile

                        Toast.makeText(
                            context,
                            "Profile clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == BottomMenuItem.Settings,
                    onClick = {
                        selectedItem = BottomMenuItem.Settings

                        Toast.makeText(
                            context,
                            "Settings clicked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = {
                        Text("Settings")
                    }
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Selected: ${selectedItem.name}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}