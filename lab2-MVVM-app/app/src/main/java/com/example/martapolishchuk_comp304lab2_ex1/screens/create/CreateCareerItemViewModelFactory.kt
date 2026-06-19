package com.example.martapolishchuk_comp304lab2_ex1.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository

// Career Item view Model Factory - factory class to create the view model
class CreateCareerItemViewModelFactory (
    private val repository: CareerItemRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCareerItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateCareerItemViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
