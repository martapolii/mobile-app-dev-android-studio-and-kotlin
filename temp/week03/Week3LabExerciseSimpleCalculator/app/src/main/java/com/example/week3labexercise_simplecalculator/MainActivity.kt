package com.example.week3labexercise_simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Marta's Calculator",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = number1,
                onValueChange = { number1 = it },
                label = { Text("First Number") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = number2,
                onValueChange = { number2 = it },
                label = { Text("Second Number") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(), // fillMaxLength would make it take full height of app (press objects to edges of screen)
                verticalAlignment = Alignment.CenterVertically
            ) {
                // add button
                item {
                    Button(
                        onClick = {
                            val n1 = number1.toIntOrNull() ?: 0
                            val n2 = number2.toIntOrNull() ?: 0
                            result = n1 + n2
                        }
                    ) {
                        Text("Add")
                    }
                }
                // subtract button
                item {
                    Button(
                        onClick = {
                            val n1 = number1.toIntOrNull() ?: 0
                            val n2 = number2.toIntOrNull() ?: 0
                            result = n1 - n2
                        }
                    ) {
                        Text("Subtract")
                    }
                }

                // multiply button
                item {
                    Button(
                        onClick = {
                            val n1 = number1.toIntOrNull() ?: 0
                            val n2 = number2.toIntOrNull() ?: 0
                            result = n1 * n2
                        }
                    ) {
                        Text("Multiply")
                    }
                }

                // divide button
                item {
                    Button(
                        onClick = {
                            val n1 = number1.toIntOrNull() ?: 0
                            val n2 = number2.toIntOrNull() ?: 0
                            result = if (n2 != 0) n1 / n2 else 0
                        }
                    ) {
                        Text("Divide")
                    }
                }
            } // LazyRow

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Result: $result",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
