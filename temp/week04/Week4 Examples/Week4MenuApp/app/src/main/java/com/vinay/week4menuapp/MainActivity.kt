package com.vinay.week4menuapp

import android.content.Context
// import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vinay.week4menuapp.ui.theme.Week4MenuAppTheme
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // simple app, so not defining theme
            ShowDropdownMenu(LocalContext.current)
        }
    }
}

@Composable
fun ShowDropdownMenu(context: Context) {
    var expanded by remember { mutableStateOf(false) }
    var backgroundColour by remember { mutableStateOf(Color.White) }
    //val context = LocalContext.current
    Box(
        modifier = Modifier
        .padding(16.dp)
        .background(backgroundColour)
    ){
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Dropdown Menu Icon")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Red") },
                onClick = {
                    Toast.makeText(context, "Red is clicked", Toast.LENGTH_SHORT).show()
                    backgroundColour = Color.Red
                } )
            DropdownMenuItem(
                text = { Text("Blue") },
                onClick = {
                    Toast.makeText(context, "Blue is clicked", Toast.LENGTH_SHORT).show()
                    backgroundColour = Color.Blue

                } )
            DropdownMenuItem(
                text = { Text("Green") },
                onClick = {
                    Toast.makeText(context, "Green is clicked", Toast.LENGTH_SHORT).show()
                    backgroundColour = Color.Green
                } )
        }

    }
}

