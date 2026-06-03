package com.example.martapolishchuk_comp304lab1_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab1_ex1.ui.theme.MartaPolishchuk_COMP304Lab1_Ex1Theme

/* HOME ACTIVITY
- display events in Lazy Column
- each event: name, location, date, indication whether upcoming or completed
- FAB: Add Event

JetPack Components:
- Lazy column for displaying list
- Card for each event
- FAB: add events

Event data class
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MartaPolishchuk_COMP304Lab1_Ex1Theme {
                    HomeActivity()
                }
            }
        }
    }
}

@Composable
fun HomeActivity(){
    // Lazy Column for displaying the list of events
    LazyColumn(
        modifier = Modifier.padding(16.dp)

    ) {
        // Cards to display each event
        foreach event in EventList{
            // create a card w the associated info
        }
        Card(

        ) {
            Text("Event Name: $eventName")
            Text("Event Location: $eventLocation")
            Text("Event Date: $eventDate")
        }

    }
}

// EVENT DATA CLASS
data class Event(
    // object parameters
    val eventName: String,
    val eventLocation: String,
    val eventDate: String
)
private val eventList = listOf(
    // list storing event info
    Event("Marta's Birthday Party", "Wonderland", "08/08/2026"),
    Event("Event 2" "Location 2", "Date 2"),
    Event("Event 3", "Location 3", "Date 3")
)

