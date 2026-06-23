package com.example.martapolishchuk_comp304_midterm.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// 301432299 - Marta Polishchuk
// implements the repository contract, so app can get/add/delete car objects

class CarRepositoryImpl : CarRepository {

    // mutable list of cars, so that data can update across multiplle activities live
    private val carList = MutableStateFlow(prepopulatedCars)

    override fun getCars(): StateFlow<List<Car>>  = carList.asStateFlow()

    override fun addCar(car: Car) {
        carList.value = carList.value + car
    }

    override fun deleteCar(car: Car) {
        carList.value = carList.value - car
    }

}