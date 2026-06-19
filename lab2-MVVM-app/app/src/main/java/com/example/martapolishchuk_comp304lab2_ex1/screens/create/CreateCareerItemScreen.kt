package com.example.martapolishchuk_comp304lab2_ex1.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.careerItemList
import com.example.martapolishchuk_comp304lab2_ex1.screens.components.CommonTopBar

// access the CreateCareerItemViewModel from this screen-level composable:
@Composable
fun CreateCareerItemScreen(
    onBackClick: () -> Unit,
    onSave: (CareerItem) -> Unit,
    viewModel: CreateCareerItemViewModel

    ){
    // store UI element values
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var progressStatus by remember { mutableStateOf("") }
    var completionIndicator by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommonTopBar(title = "Student Career Development Hub")
        } // topBar

    ) { paddingValues ->
        // Put data members of a Career Item object into a column:
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Text("Create a new career item:", style = MaterialTheme.typography.titleMedium)

                // 1. Title
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                // 2. Category
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth()
                )

                // 3. Progress Status
                OutlinedTextField(
                    value = progressStatus,
                    onValueChange = { progressStatus = it },
                    label = { Text("Progress Status") },
                    modifier = Modifier.fillMaxWidth()
                )

                // 4. Completion Indicator
                // Switch to indicate whether career item was completed or not
                Text(text = "Career Item Completed: ")

                Switch(
                    checked = completionIndicator,
                    onCheckedChange = { completionIndicator = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF009688),
                        uncheckedThumbColor = Color(0xFFD05B52),
                    )
                )

                // Button to confirm creation
                Button(
                    onClick = {
                        viewModel.addCareerItem(
                            title,
                            category,
                            progressStatus,
                            completionIndicator
                        )
                        title = ""
                        category = ""
                        progressStatus = ""
                        completionIndicator = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Career Item")
                }

            } // column

    }
}


