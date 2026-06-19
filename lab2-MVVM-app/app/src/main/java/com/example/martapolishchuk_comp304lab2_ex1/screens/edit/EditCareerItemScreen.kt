package com.example.martapolishchuk_comp304lab2_ex1.screens.edit

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar

/*
VIEW/EDIT CAREER EVEN ITEM ---------------------------------------------------------------------------
- opens when career item is clicked on Home Screen
- pre-populated text fields
- button: Save - saves changes & returns to Home Screen

 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCareerItemScreen(
    event: CareerItem,
    onSave: (CareerItem) -> Unit
){
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var progressStatus by remember { mutableStateOf("") }
    var completionIndicator by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopBar(title = "Student Career Development Hub")
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
                // Text fields for viewing and editing career item details
                // title
                OutlinedTextField(
                    value = title ,
                    onValueChange = {title = it},
                    label = {Text("Title")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                // category - ** CHANGE TO DROP DOWN **
                OutlinedTextField(
                    value = category ,
                    onValueChange = {category = it},
                    label = {Text("Category:")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )
               // progress status - ** CHANGE TO DROP DOWN **
                OutlinedTextField(
                    value = progressStatus ,
                    onValueChange = {progressStatus = it},
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

                    // Switch to indicate whether career item was completed or not
                    Text(text="Career Item Completed: ")

                    Switch(
                        checked = completionIndicator,
                        onCheckedChange = {completionIndicator = it},
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFF009688),
                            uncheckedThumbColor = Color(0xFFD05B52),
                        )
                    )
                } // Row
            } // card

            // Save Button
            Button(onClick = {onSave(CareerItem(title, category, progressStatus, completionIndicator))},
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