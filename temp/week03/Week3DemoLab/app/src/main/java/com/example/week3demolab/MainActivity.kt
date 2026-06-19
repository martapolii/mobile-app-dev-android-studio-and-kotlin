package com.example.week3demolab

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3demolab.ui.theme.Week3DemoLabTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate is mandatory ljfecycle method
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // call composable functions here
            //MyApp() // < that's it!
            //StyledText()
            //GreetingColumn()
            //ButtonRow()
            IconTextOverlay()

        }
    }
}

// composable functions are outside main activity always

// creates a button + counter:
@Composable
fun MyApp() {
    var count by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Counter: $count", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}

// adds stylized text:
@Composable
fun StyledText() {
    Text(
        text = "Hello, Compose!",
//adds padding around the text
        modifier = Modifier.padding(24.dp),
        style = TextStyle(
            color = Color.Blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

// COMPOSABLE LAYOUTS

// column layout:
@Composable
fun GreetingColumn() {
// stack Text elements vertically
    Column {
        Text(text = "Hello, Jetpack Compose!")
        Text(text = "This is the second line.")
        Text(text = "And here's the third.")
    }
}

// row layout - 2 buttons
@Composable
fun ButtonRow() {
    Row { // arrange the buttons horizontally
        Button(onClick = { /* Handle click */ }) {
            Text("Button 1") // define button text here
        }
        Button(onClick = { /* Handle click */ }) {
            Text("Button 2")
        }
    }
}

// box layout
@Composable
fun IconTextOverlay() {
// create a container with a light gray background
    Box(
        modifier = Modifier.size(150.dp).background(color = Color.LightGray).padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Look here!", Modifier.align(Alignment.Center))
// Icon component aligned to the top end of the Box, overlapping the text
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.align(Alignment.TopEnd).size(40.dp)
        )
    }
}

