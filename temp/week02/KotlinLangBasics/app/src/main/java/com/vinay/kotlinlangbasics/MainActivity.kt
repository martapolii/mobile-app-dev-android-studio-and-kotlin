package com.vinay.kotlinlangbasics

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vinay.kotlinlangbasics.ui.theme.KotlinLangBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeApp() // reference composable functions here, code in the functions themselves
        }
    }
}

@Composable
fun MyComposeApp() {
    val context = LocalContext.current

    // Used in lambda button and filter example
    val sumLambda = { a: Int, b: Int -> a + b }
    val numbers = arrayOf(1, -2, 3, -4, 5)

    Column(modifier = Modifier.padding(16.dp)) {

        // the below are controls + associated logic. Not css

        // 1st button
        Button(onClick = {
            val sum = sum(5, 6)
            Toast.makeText(context, "sum=$sum", Toast.LENGTH_SHORT).show()
            printSum1(context, 7, 8)
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Functions")
        }
// 2nd Button
        Button(onClick = {
            val numberList = listOf(1, 2, 3, 4, 5, 6)
            numberList.forEach {
                Toast.makeText(context, "number=$it", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Loops")
        }
// 3rd
        Button(onClick = {
            val str = describe("some string")
            Toast.makeText(context, "description=$str", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("When Expression")
        }
// 4th
        Button(onClick = {
            val result = sumLambda(9, 3)
            Toast.makeText(context, "sum from lambda=$result", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Lambda")
        }
// 5th
        Button(onClick = {
            val positives = numbers.filter { it > 0 }
            positives.forEach {
                Toast.makeText(context, "number = $it", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("It Lambda")
        }
// 6th
        Button(onClick = {
            val person = Person("John", "Stone", 27)
            Toast.makeText(context, "person = ${person.upperCaseFirstName}, ${person.upperCaseLastName}, ${person.age}", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Kotlin Class")
        }
// 7th
        Button(onClick = {
            val user = User("john stone", 27)
            Toast.makeText(context, "user = ${user.name}, ${user.age}", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("Data Class")
        }
    }
}

// Functions
fun sum(a: Int, b: Int): Int = a + b

fun printSum1(context: android.content.Context, a: Int, b: Int) {
    Toast.makeText(context, "sum of $a and $b is ${a + b}", Toast.LENGTH_SHORT).show()
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }
