package com.example.martapolishchuk_comp304_midterm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.CarFormState
import com.example.martapolishchuk_comp304_midterm.data.CarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 301432299 - Marta Polishchuk
data class DetailUiState(
    val formState: CarFormState = CarFormState(),
    val screenMode: CarInventoryDisplayScreenMode = CarInventoryDisplayScreenMode.VIEW,
    val validationMessage: String? = null
)


class CarInventoryDisplayViewModel(
    private val repository: CarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CarInventoryDisplayUiState())
    val uiState: StateFlow<com.example.martapolishchuk_comp304_midterm.viewmodels.CarInventoryDisplayUiState> = _uiState.asStateFlow()

    fun onMakeAndModelChange(newValue: String) = updateForm { it.copy(makeAndModel = newValue) }
    fun onSellerNameChange(newValue: String) = updateForm { it.copy(sellerName = newValue) }
    fun onVehicleTypeChange(newValue: String) = updateForm { it.copy(vehicleType = newValue) }
    fun onManufacturingYearChange(newValue: String) = updateForm { it.copy(manufacturingYear = newValue) }
    fun onSellingPriceChange(newValue: String) = updateForm { it.copy(sellingPrice = newValue) }

    // delete car
     private fun deleteCar() {
         val currentState = _uiState.value
         repository.deleteCar(currentState.formState.toCar(idOverride = -1))
     }


class CarInventoryDisplayViewModelFactory(
    private val repository: CarRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarInventoryDisplayViewModel::class.java)) {
            return CarInventoryDisplayViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}