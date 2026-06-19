package com.example.inputformapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inputformapp.ui.theme.InputFormAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserFormScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen() {

    // TextField State
    var name by remember {
        mutableStateOf("")
    }

    // Checkbox States
    var reading by remember {
        mutableStateOf(false)
    }

    var music by remember {
        mutableStateOf(false)
    }

    var gaming by remember {
        mutableStateOf(false)
    }

    // Radio Button State
    val sportsList = listOf(
        "Cricket",
        "Football",
        "Basketball"
    )

    var selectedSport by remember {
        mutableStateOf(sportsList[0])
    }

    // Result State
    var result by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sports Registration Form")
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    val hobbies = mutableListOf<String>()

                    if (reading) hobbies.add("Reading")
                    if (music) hobbies.add("Music")
                    if (gaming) hobbies.add("Gaming")

                    result = """
                        Name: $name
                        
                        Hobbies: ${
                        if (hobbies.isEmpty())
                            "None"
                        else
                            hobbies.joinToString(", ")
                    }
                        
                        Favorite Sport: $selectedSport
                    """.trimIndent()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Submit"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Enter Your Details",
                style = MaterialTheme.typography.headlineSmall
            )

            // TextField
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider()

            // Checkboxes
            Text(
                text = "Select Hobbies",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = reading,
                    onCheckedChange = {
                        reading = it
                    }
                )
                Text("Reading")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = music,
                    onCheckedChange = {
                        music = it
                    }
                )
                Text("Music")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = gaming,
                    onCheckedChange = {
                        gaming = it
                    }
                )
                Text("Gaming")
            }

            HorizontalDivider()

            // Radio Buttons
            Text(
                text = "Favorite Sport",
                style = MaterialTheme.typography.titleMedium
            )

            sportsList.forEach { sport ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedSport == sport,
                            onClick = {
                                selectedSport = sport
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = selectedSport == sport,
                        onClick = {
                            selectedSport = sport
                        }
                    )

                    Text(
                        text = sport
                    )
                }
            }

            HorizontalDivider()

            Text(
                text = "Submitted Information",
                style = MaterialTheme.typography.titleMedium
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (result.isEmpty())
                        "Click the FAB to display entered details."
                    else
                        result,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
