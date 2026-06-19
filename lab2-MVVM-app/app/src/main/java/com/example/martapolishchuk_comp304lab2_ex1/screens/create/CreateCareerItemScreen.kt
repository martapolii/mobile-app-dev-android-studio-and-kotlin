package com.example.martapolishchuk_comp304lab2_ex1.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.martapolishchuk_comp304lab2_ex1.screens.home.CareerItemViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository
import com.example.martapolishchuk_comp304lab2_ex1.ui.theme.MartaPolishchuk_COMP304Lab2_Ex1Theme

@Composable
fun CreateCareerItemScreen(viewModel: CareerItemViewModel){
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var progressStatus by remember { mutableStateOf("") }
    var completionIndicator by remember { mutableStateOf(" ") }

    val careerItemList by viewModel.careerItems.observeAsState(initial = emptyList())

    // Put data members of a Career Item object into a column:
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Create a new career item:", style = MaterialTheme.typography.titleMedium)

        // 1. Title
        OutlinedTextField(
            value = title ,
            onValueChange = { title = it},
            label = {Text("Title")},
            modifier = Modifier.fillMaxWidth()
        )

        // 2. Category
        OutlinedTextField(
            value = category ,
            onValueChange = { category = it},
            label = {Text("Category")},
            modifier = Modifier.fillMaxWidth()
        )

        // 3. Progress Status
        OutlinedTextField(
            value = progressStatus ,
            onValueChange = { progressStatus = it},
            label = {Text("Progress Status")},
            modifier = Modifier.fillMaxWidth()
        )

        // 4. Completion Indicator
        OutlinedTextField(
            value = completionIndicator ,
            onValueChange = { completionIndicator = it},
            label = {Text("Completion Indicator")},
            modifier = Modifier.fillMaxWidth()
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
                completionIndicator = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Career Item")
        }


    } // column



}

@Preview(showBackground = true)
@Composable
fun CreateCareerItemPreview() {
    // 1. Create an anonymous implementation of the CareerItemRepository interface
    val mockRepository = object : CareerItemRepository {
        override fun getCareerItems(): LiveData<List<CareerItem>> = MutableLiveData(
            listOf(
                CareerItem("Sample Career Item", "Skill Development", "In Progress", "50%")
            )
        )
        // No-op implementation for adding items in a preview
        override fun addCareerItem(careerItem: CareerItem) {}
    }

    // 2. Instantiate the real ViewModel using the mock repository
    val mockViewModel = CareerItemViewModel(mockRepository)

    MartaPolishchuk_COMP304Lab2_Ex1Theme {
        CreateCareerItemScreen(
            viewModel = mockViewModel
        )
    }
}
