package com.example.martapolishchuk_comp304_midterm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.Car
import com.example.martapolishchuk_comp304_midterm.data.CarFormState
import com.example.martapolishchuk_comp304_midterm.data.CarRepository
import com.example.martapolishchuk_comp304_midterm.data.validateCarInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 301432299 - Marta Polishchuk
data class CreateCarUiState(
    val formState: CarFormState = CarFormState(),
    val validationMessage: String? = null
)

class CarEntryViewModel(
    private val repository: CarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateCarUiState())
    val uiState: StateFlow<CreateCarUiState> = _uiState.asStateFlow()

    fun onMakeAndModelChange(newValue: String) = updateForm { it.copy(makeAndModel = newValue) }
    fun onSellerNameChange(newValue: String) = updateForm { it.copy(sellerName = newValue) }
    fun onVehicleTypeChange(newValue: String) = updateForm { it.copy(vehicleType = newValue) }
    fun onManufacturingYearChange(newValue: String) = updateForm { it.copy(manufacturingYear = newValue) }
    fun onSellingPriceChange(newValue: String) = updateForm { it.copy(sellingPrice = newValue) }

      //adds  new car item to the shared repo

    fun saveCar(): Boolean {
        val currentState = _uiState.value
        val validationMessage = validateCarInput(
            makeAndModel = currentState.formState.makeAndModel,
            sellerName = currentState.formState.sellerName,
            vehicleType = currentState.formState.vehicleType,
            manufacturingYear = currentState.formState.manufacturingYear,
            sellingPrice = currentState.formState.sellingPrice
        )

        if (validationMessage != null) {
            _uiState.value = currentState.copy(validationMessage = validationMessage)
            return false
        }

        repository.addCar(
            Car(
                makeAndModel = currentState.formState.makeAndModel,
                sellerName = currentState.formState.sellerName,
                vehicleType = currentState.formState.vehicleType,
                manufacturingYear = currentState.formState.manufacturingYear.toInt(),
                sellingPrice = currentState.formState.sellingPrice.toDouble()
            )
        )
        _uiState.value = currentState.copy(validationMessage = null)
        return true
    }

    private fun updateForm(
        transform: (CarFormState) -> CarFormState
    ) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            formState = transform(currentState.formState),
            validationMessage = null
        )
    }
}


class CarEntryViewModelFactory(
    private val repository: CarRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarEntryViewModel::class.java)) {
            return CarEntryViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
