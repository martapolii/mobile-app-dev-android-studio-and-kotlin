package com.example.martapolishchuk_comp304_midterm.data

import kotlinx.coroutines.flow.StateFlow

// 301432299 - Marta Polishchuk


// repository contract used by every screen
// the Ui talks to the repo instead of modifying the data list directly

interface CarRepository {
    fun getCars(): StateFlow<List<Car>> // get cars from repo to display on screen
    fun addCar(car: Car) // add a new car to the repo
    fun deleteCar(car: Car) // delete a car from the repo
}
