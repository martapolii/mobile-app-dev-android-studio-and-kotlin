package com.example.martapolishchuk_comp304_midterm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.Car
import com.example.martapolishchuk_comp304_midterm.data.CarRepository
import kotlinx.coroutines.flow.StateFlow

// 301432299 - Marta Polishchuk
class CarInventoryDisplayViewModel(
    private val repository: CarRepository
) : ViewModel() {

    val cars: StateFlow<List<Car>> = repository.getCars()

    // delete car
    fun deleteCar(car: Car) {
        repository.deleteCar(car)
    }
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
