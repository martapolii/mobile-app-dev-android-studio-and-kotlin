package com.example.martapolishchuk_comp304_midterm.data
// 301432299 - Marta Polishchuk

// shared repository holder - shown in week 6 RetroFitClient - uses singleton object that multiple screens can access while the app is running
object CarRepositoryProvider {

    val repository: CarRepository by lazy {
        CarRepositoryImpl()
    }


}