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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.CarRepositoryProvider
import com.example.martapolishchuk_comp304_midterm.ui.theme.MartaPolishchuk_COMP304_MidtermTheme
import com.example.martapolishchuk_comp304_midterm.view.components.CarForm
import com.example.martapolishchuk_comp304_midterm.view.components.CommonTopBar

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
        val detailViewModel = ViewModelProvider(this, factory)[CarInventoryDisplayViewModel::class.java]
        val carId = intent.getIntExtra(EXTRA_CAR_ID, -1)
        val screenMode = readScreenMode()

        setContent {
            MartaPolishchuk_COMP304_MidtermTheme {
                DetailScreen(
                    carId = carId,
                    screenMode = screenMode,
                    CarInventoryDisplayViewModel = CarInventoryDisplayViewModel,
                    onSaveComplete = { finish() }
                )
            }
        }
    }

    private fun readScreenMode(): CarInventoryDisplayScreenMode {
        val rawMode = intent.getStringExtra(EXTRA_SCREEN_MODE) ?: DetailScreenMode.VIEW.name

        return runCatching {
            CarInventoryDisplayScreenMode.valueOf(rawMode)
        }.getOrDefault(CarInventoryDisplayScreenMode.EDIT)
    }

    companion object {
        const val EXTRA_CAR_ID = "extra_car_id"
        const val EXTRA_SCREEN_MODE = "extra_screen_mode"

        /**
         * This template passes the candy id and screen mode.
         *
         * If you later want the full model instead, you can make Candy Parcelable and
         * place the object into the Intent here.
         */
        fun createIntent(
            context: Context,
            candyId: Int,
            screenMode: CarInventoryDisplayScreenMode
        ): Intent {
            return Intent(context, CarInventoryDisplayActivity::class.java).apply {
                putExtra(EXTRA_CAR_ID, carId)
                putExtra(EXTRA_SCREEN_MODE, screenMode.name)
            }
        }
    }
}

@Composable
fun CarInventoryDisplayScreen(
    carId : Int,
    screenMode: CarInventoryDisplayScreenMode,
    CarInventoryDisplayModel: CarInventoryDisplayViewModel,
    onSaveComplete: () -> Unit
) {
    LaunchedEffect(carId , screenMode) {
        CarInventoryDisplayModel.loadCar(carId = carId , screenMode = screenMode)
    }

    val uiState by CarInventoryDisplayModel.uiState.collectAsState()
    val isReadOnly = uiState.screenMode == CarInventoryDisplayScreenMode.VIEW

    Scaffold(
        topBar = {
            CommonTopBar(
                title = when (uiState.screenMode) {
                    CarInventoryDisplayScreenMode.VIEW -> "View Car"
                    CarInventoryDisplayScreenMode.CREATE -> "Create Car"
                },
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState()
        ) {

            item {
                CarForm(
                    formState = uiState.formState,
                    validationMessage = uiState.validationMessage,
                    readOnly = isReadOnly,
                    primaryButtonText = "Save Changes",
                    showPrimaryButton = !isReadOnly,
                    onMakeAndModelChange = CarInventoryDisplayModel::onMakeAndModelChange,
                    onSellerNameChange = CarInventoryDisplayModel::onSellerNameChange,
                    onVehicleTypeChange = CarInventoryDisplayModel::onVehicleTypeChange,
                    onManufacturingYearChange = CarInventoryDisplayModel::onManufacturingYearChange,
                    onSellingPriceChange = CarInventoryDisplayModel::onSellingPriceChange,
                    onPrimaryButtonClick = {
                        if (CarInventoryDisplayViewModel.saveCar()) {
                            onSaveComplete()
                        }
                    }
                )
            }

        }
    }
}