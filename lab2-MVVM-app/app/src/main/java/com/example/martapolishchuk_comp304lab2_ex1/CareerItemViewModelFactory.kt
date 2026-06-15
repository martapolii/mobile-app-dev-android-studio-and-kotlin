package com.example.martapolishchuk_comp304lab2_ex1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Career Item view Model Factory - factory class to create the view model

class CareerItemViewModelFactory(
    private val repository: CareerItemRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CareerItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CareerItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}