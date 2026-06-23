package com.example.martapolishchuk_comp304_midterm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304_midterm.data.Car
import com.example.martapolishchuk_comp304_midterm.data.CarRepository
import kotlinx.coroutines.flow.StateFlow

// 301432299 - Marta Polishchuk

class HomeViewModel(
    private val repository: CarRepository
) : ViewModel() {
    val candies: StateFlow<List<Car>> = repository.getCars()

    // add car
    fun addCar(candy: Car) {
        repository.addCar(candy)
    }

    fun deleteCar(candy: Car) {
        repository.deleteCar(candy)
    }
}

class MainActivityViewModelFactory(
    private val repository: CarRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(com.example.martapolishchuk_comp304_midterm.viewmodels.MainActivityViewModelFactory::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}