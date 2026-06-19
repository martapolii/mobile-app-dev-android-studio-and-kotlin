package com.example.martapolishchuk_comp304lab2_ex1.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
VIEW/EDIT EVENT ACTIVITY ---------------------------------------------------------------------------
- opens when event is clicked on Home Activity Page
- pre-populated text fields with: name, location, date
- marked as completed or upcoming
- button: Save - saves changes & returns to Home Activity

JetPack components:
- pre-filled Text Fields
- checkbox or switch to mark event completion status
- button: Save
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCareerItemScreen(
    event: Event,
    onSave: (Event) -> Unit
){
    var eventName by remember { mutableStateOf(event.eventName) }
    var eventLocation by remember { mutableStateOf(event.eventLocation) }
    var eventDate by remember { mutableStateOf(event.eventDate) }
    var completedEvent by remember { mutableStateOf(event.completedEvent) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title ={Text("View / Edit Event")},
            )
        } // topBar

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Text fields for event name, location, date
                OutlinedTextField(
                    value = eventName ,
                    onValueChange = {eventName = it},
                    label = {Text("Event name:")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = eventLocation,
                    onValueChange = {eventLocation = it},
                    label = {Text("Event location:")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = eventDate,
                    onValueChange = {eventDate = it},
                    label = {Text("Event date:")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    // Switch to indicate whether event was completed or not
                    Text(text="Event Completed: ")

                    Switch(
                        checked = completedEvent,
                        onCheckedChange = {completedEvent = it},
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF009688),
                            uncheckedThumbColor = Color(0xFFD05B52),
                        )
                    )
                } // Row
            } // card

            // Save Button
            Button(onClick = {onSave(Event(eventName, eventLocation, eventDate, completedEvent))},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Event")
            }
        } // Column
    }
}