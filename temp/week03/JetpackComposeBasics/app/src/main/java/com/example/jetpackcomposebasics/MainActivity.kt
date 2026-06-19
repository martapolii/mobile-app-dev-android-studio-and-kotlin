package com.example.jetpackcomposebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposebasics.ui.theme.ChapterThreeTheme
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleLayouts()
        }
    }
}

@Composable
fun ExampleLayouts() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleColumn()
        Spacer(modifier = Modifier.height(20.dp))
        SimpleRow()
        Spacer(modifier = Modifier.height(20.dp))
        SimpleBox()
        Spacer(modifier = Modifier.height(20.dp))
        SimpleLazyColumn()
        Spacer(modifier = Modifier.height(20.dp))
        SimpleLazyRow()
    }
}

@Composable
fun SimpleColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Android", fontSize = 20.sp)
        Text(text = "Kotlin", fontSize = 20.sp)
        Text(text = "Compose", fontSize = 20.sp)
    }
}

@Composable
fun SimpleRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Android", fontSize = 20.sp)
        Text(text = "Kotlin", fontSize = 20.sp)
        Text(text = "Compose", fontSize = 20.sp)
    }
}

@Composable
fun SimpleBox() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Notifications,
            contentDescription = null,
            tint = Color.Green,
            modifier = Modifier.size(80.dp)
        )
        Text(text = "9", color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun SimpleLazyColumn() {
    var clickedItem by remember { mutableStateOf(-1) }  // State to track the last clicked item

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        items(100) { index ->
            Text(
                text = if (clickedItem == index) "Clicked: Item number $index" else "Item number $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { clickedItem = index }  // Update clickedItem on click
                    .background(if (clickedItem == index) Color.Cyan else Color.Transparent)
                    .padding(16.dp),
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun SimpleLazyRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        items(100) { index ->
            Text(
                text = "Item number $index",
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}
