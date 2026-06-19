package com.example.week2simpleapp

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week2simpleapp.ui.theme.Week2SimpleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Change alignment of content
            )
            {
                Button(
                    onClick = {
                        // creating a button that leads to another Kotline file (SecondActivity)
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)

                        // creating a 'toast' message when button is clicked
                        Toast.makeText(this@MainActivity, "Button Clicked", Toast.LENGTH_LONG).show()
                    }
                ) {
                    Text(text = "Go to Second Activity",
                        fontSize = 14.sp,
                        color = Color.Cyan)
                }
            }
        }
    }
}

