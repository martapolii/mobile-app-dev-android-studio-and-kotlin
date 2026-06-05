package com.example.martapolishchuk_comp304lab1_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab1_ex1.ui.theme.MartaPolishchuk_COMP304Lab1_Ex1Theme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MartaPolishchuk_COMP304Lab1_Ex1Theme {
                App()
            }
        }
    }
}


// EVENT DATA CLASS --------------------------------------------------------------------------------
data class Event(
    // object parameters
    val eventName: String,
    val eventLocation: String,
    val eventDate: String,
    var completedEvent: Boolean = false
)

// APP ---------------------------------------------------------------------------------------------
// made this to control switching between the different activties, and to pass along the variables that store states
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {

    // use variables to store states that will be passed into the 3 activities
    var currentScreen by remember { mutableStateOf("home") }
    var events = remember { mutableStateListOf(
        Event("COMP304 Lab", "Centennial College", "2026-06-09"),
        Event("COMP254 Lecture", "Centennial College", "2026-05-27"),
        Event("COMP254 Lab", "Centennial College", "2026-05-28")
    ) }
    var selectedEventIndex by remember { mutableIntStateOf(-1) }

    when (currentScreen) {

        "home" -> HomeActivity(
            events = events,
            onAddClick = { currentScreen = "add" },
            onEventClick = { index ->
                selectedEventIndex = index
                currentScreen = "edit"
            }
        )

        "add" -> AddEventActivityScreen(
            onSave = { newEvent ->
                events.add(newEvent)
                currentScreen = "home"
            },
            onBack = { currentScreen = "home" }
        )

        "edit" -> {
            if (selectedEventIndex >= 0) {
                ViewEditEventActivityScreen(
                    event = events[selectedEventIndex],
                    onSave = { updatedEvent ->
                        events[selectedEventIndex] = updatedEvent
                        currentScreen = "home"
                    },
                    onBack = { currentScreen = "home" }
                )
            }
        }
    }
}

/* HOME ACTIVITY -----------------------------------------------------------------------------------
- display events in Lazy Column
- each event: name, location, date, indication whether upcoming or completed
- FAB: Add Event

JetPack Components:
- Lazy column for displaying list
- Card for each event
- FAB: add events

Event data class
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeActivity(
    events: List<Event>,
    onAddClick: () -> Unit,
    onEventClick: (Int) -> Unit
) {
    // added a Scaffold to each screen to hold a top app bar + column with main content. Also made it easier to place the FAB
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("All Events") },
            )
        },
        floatingActionButton = {
            // add event button
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Text("+")
            }
        } // floatingActionButton
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ){
            itemsIndexed(events) { index, event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { onEventClick(index) }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text("Event name: " + event.eventName)
                        Text("Location: " + event.eventLocation)
                        Text("Date: " + event.eventDate)

                        Text(
                            if (event.completedEvent)
                                "Status: Completed"
                            else
                                "Status: Upcoming"
                        )
                    }
                }
            } //itemsIndexed

        } // lazyColumn
    } // Scaffold
} // home activity

/*
ADD EVENT ACTIVITY ---------------------------------------------------------------------------------
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventActivityScreen(
    // lambda parameters (function being passes into another function):
    onSave: (Event) -> Unit, // accepts an event and returns nothing (unit) - tells you what to do when user saves
    onBack: () -> Unit // takes no parameters, returns nothing - tells you what to do when user goes back
) {
    //  need variables to store the values of the inputs for name, location & date - MAY NEED TO PUT THESE IN THE DATA CLASS!!!
    var eventName by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var eventDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Add New Event")}
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // Text Field 1 - EVENT NAME:
            OutlinedTextField(
                value = eventName,
                onValueChange = { eventName = it },
                label = { Text("Event Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text Field 2 - EVENT LOCATION:
            OutlinedTextField(
                value = eventLocation,
                onValueChange = { eventLocation = it },
                label = { Text("Event Location") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text Field 3 - EVENT DATE:
            OutlinedTextField(
                value = eventDate,
                onValueChange = { eventDate = it },
                label = { Text("Event Date") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // SAVE BUTTON
            Button(
                onClick = { onSave(Event(eventName, eventLocation, eventDate)) } // onClick an event object is created, onSave is called
            ) {
                Text("Save")
            }
        } // column

    }



}

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
fun ViewEditEventActivityScreen(
    event: Event,
    onSave: (Event) -> Unit,
    onBack: () -> Unit
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
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Text fields for event name, location, date
            OutlinedTextField( value = eventName , onValueChange = {eventName = it}, label = {Text("Event name:")})
            OutlinedTextField(value = eventLocation, onValueChange = {eventLocation = it}, label = {Text("Event location:")})
            OutlinedTextField(value = eventDate, onValueChange = {eventDate = it}, label = {Text("Event date:")})

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text="Event Completed: ")
                // Switch to indicate whether event was completed or not
                Switch(
                    checked = completedEvent,
                    onCheckedChange = {completedEvent = it}
                )
            } // Row

            // Save Button
            Button(onClick = {onSave(Event(eventName, eventLocation, eventDate, completedEvent))}
            ) {
                Text("Save")
            }
        } // Column
    }
}


