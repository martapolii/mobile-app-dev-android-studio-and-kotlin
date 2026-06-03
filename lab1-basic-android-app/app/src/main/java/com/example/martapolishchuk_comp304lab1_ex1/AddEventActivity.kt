package com.example.martapolishchuk_comp304lab1_ex1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/*
ADD EVENT ACTIVITY
- input fields:
    - event title
    - location
    - date (basic text field)
- button: Save, returns to home activity

JetPack components:
- Column
- TextField for name, location, date
- Button: save event
 */

@Composable
fun AddEventActivityScreen() {
    //  need variables to store the values of the inputs for name, location & date - MAY NEED TO PUT THESE IN THE DATA CLASS!!!
    var eventName by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var eventDate by remember {mutableStateOf("")}

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // Using a column to store all items on this page
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Adding a title to the page
            Text(
                text = "Add New Event",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Text Field 1 - EVENT NAME:
            OutlinedTextField(
                value = eventName,
                onValueChange = {eventName = it},
                label = {Text("Event Name")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Text Field 2 - EVENT LOCATION:
            OutlinedTextField(
                value = eventLocation,
                onValueChange = {eventLocation = it},
                label = {Text("Event Location")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Text Field 3 - EVENT DATE:
            OutlinedTextField(
                value = eventDate,
                onValueChange = {eventDate = it},
                label = {Text("Event Date")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )

            // SAVE BUTTON
            Button(
                onClick =  { }
            )
            {
                Text("Save")
            }
        } // column

    } // surface



}