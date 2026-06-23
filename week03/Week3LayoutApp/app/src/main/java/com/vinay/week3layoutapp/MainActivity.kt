package com.vinay.week3layoutapp

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinay.week3layoutapp.ui.theme.Week3LayoutAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //calling a composable function
            ColumnLayout()
            BoxLayout()
            LazyColumnLayout()
        }
    }

   //defining a composable function to design UI
    @Composable
    fun ColumnLayout() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        )
        {
            //label
            Text(text = "Kotlin", fontSize = 20.sp)
            Text(text = "Java", fontSize = 20.sp)
            Text(text = "Groovy", fontSize = 20.sp)
        }
    }

    @Composable
    fun BoxLayout() {
        Box(
            modifier = Modifier
                .padding(100.dp)
                .size(200.dp)
                .background(Color.Cyan)
        )
        {
           /* Text(text ="Top Start",
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(text ="Center",
                modifier = Modifier.align(Alignment.Center)
            )
            Text(text ="Bottom End",
                modifier = Modifier.align(Alignment.BottomEnd)
            )*/
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = "Kotlin", fontSize = 20.sp)
                Text(text = "Java", fontSize = 20.sp)
                Text(text = "Groovy", fontSize = 20.sp)
            }
        }
    }
    @Composable
    fun LazyColumnLayout() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            items(100) { index->
                Text(text = "Item $index", fontSize = 20.sp)
            }
        }
    }

}

