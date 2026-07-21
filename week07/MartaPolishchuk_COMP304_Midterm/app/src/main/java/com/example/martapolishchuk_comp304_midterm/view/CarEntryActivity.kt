// 301432299 - Marta Polishchuk
package com.example.martapolishchuk_comp304_midterm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
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
import com.example.martapolishchuk_comp304_midterm.view.components.CarForm
import com.example.martapolishchuk_comp304_midterm.view.components.CommonTopBar
import com.example.martapolishchuk_comp304_midterm.viewmodels.CarEntryViewModel
import com.example.martapolishchuk_comp304_midterm.viewmodels.CarEntryViewModelFactory

/*
Second Activity (Car Entry Screen)

-> Allow users to add a used car with the following information:
-Car Make and Model
-Seller Name
-Vehicle Type -> use Segmented Button
    - Sedan, SUV, Hatchback
-Manufacturing Year
-Selling Price

-> Add Car Functionality
When the user taps the Add Car button:

1. Validate that all fields are completed.
2. Create a new car record.
3. Display a Snackbar containing:
- Make and Model
- Seller Name
- Vehicle Type
- Manufacturing Year
- Selling Price
4.Add the car to the inventory list.
 */

class CarEntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = CarRepositoryProvider.repository
        val factory = CarEntryViewModelFactory(repository)
        val carEntryViewModel = ViewModelProvider(this, factory)[CarEntryViewModel::class.java]

        setContent {
            MartaPolishchuk_COMP304_MidtermTheme {
                CreateCarScreen(
                    createCarViewModel = carEntryViewModel,
                    onBackClick = { finish() },
                    onSaveComplete = { finish() }
                )
            }
        }
    }

    // used
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, com.example.martapolishchuk_comp304_midterm.view.CarEntryActivity::class.java)
        }
    }
}

@Composable
fun CreateCarScreen(
    createCarViewModel: CarEntryViewModel,
    onBackClick: () -> Unit,
    onSaveComplete: () -> Unit
) {
    val uiState by createCarViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "Sell Your Car",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                CarForm(
                    formState = uiState.formState,
                    validationMessage = uiState.validationMessage,
                    readOnly = false,
                    onMakeAndModelChange = createCarViewModel::onMakeAndModelChange,
                    onSellerNameChange = createCarViewModel::onSellerNameChange,
                    onVehicleTypeChange = createCarViewModel::onVehicleTypeChange,
                    onManufacturingYearChange = createCarViewModel::onManufacturingYearChange,
                    onSellingPriceChange = createCarViewModel::onSellingPriceChange,
                    onAddCarClick = {
                        if (createCarViewModel.saveCar()) {
                            onSaveComplete()
                        }
                    }
                )
            }
        }
    }
}
