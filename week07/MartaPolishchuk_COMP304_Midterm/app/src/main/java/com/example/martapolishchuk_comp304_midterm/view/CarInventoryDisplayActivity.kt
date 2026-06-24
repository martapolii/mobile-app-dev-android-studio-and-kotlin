// 301432299 - Marta Polishchuk
package com.example.martapolishchuk_comp304_midterm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.CarRepositoryProvider
import com.example.martapolishchuk_comp304_midterm.ui.theme.MartaPolishchuk_COMP304_MidtermTheme
import com.example.martapolishchuk_comp304_midterm.view.components.CommonTopBar
import com.example.martapolishchuk_comp304_midterm.view.components.carListItem
import com.example.martapolishchuk_comp304_midterm.viewmodels.CarInventoryDisplayViewModel
import com.example.martapolishchuk_comp304_midterm.viewmodels.CarInventoryDisplayViewModelFactory

/*
Optional - 3rd Activity - Car Inventory List

Added this to display the Car Inventory list. (I know I could have made 2 screens in one activity, but I prefer the modularity of having an activity & viewmodel per screen).

To-do:
Car Inventory Display

-> Display all added cars using a LazyColumn.
Each inventory item should display:
-Make and Model
-Seller Name
-Vehicle Type
-Manufacturing Year
-Selling Price
-Use Material 3 Card components for each car entry.

-> Delete car functionality
Add a Delete button to each car item in the LazyColumn. When clicked, the selected car is removed from the list and a Snackbar confirms the deletion.
 */

class CarInventoryDisplayActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CarRepositoryProvider.repository
        val factory = CarInventoryDisplayViewModelFactory(repository)
        val carInventoryDisplayViewModel =
            ViewModelProvider(this, factory)[CarInventoryDisplayViewModel::class.java]

        setContent {
            MartaPolishchuk_COMP304_MidtermTheme {
                CarInventoryDisplayScreen(
                    carInventoryDisplayViewModel = carInventoryDisplayViewModel,
                    onBackClick = { finish() },
                    onAddCarClick = {
                        startActivity(CarEntryActivity.createIntent(this))
                    }
                )
            }
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, CarInventoryDisplayActivity::class.java)
        }
    }
}

@Composable
fun CarInventoryDisplayScreen(
    carInventoryDisplayViewModel: CarInventoryDisplayViewModel,
    onBackClick: () -> Unit,
    onAddCarClick: () -> Unit
) {
    val carList by carInventoryDisplayViewModel.cars.collectAsState()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Car Inventory",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onAddCarClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Car")
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = carList) { car ->
                    carListItem(
                        car = car,
                        onDeleteClick = {
                            carInventoryDisplayViewModel.deleteCar(car)
                        }
                    )
                }
            }
        }
    }
}
