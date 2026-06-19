package com.example.martapolishchuk_comp304lab2_ex1.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository

// Career Item View Model -

class CareerItemViewModel(private val repository: CareerItemRepository) : ViewModel() {

    val careerItems: LiveData<List<CareerItem>> = repository.getCareerItems()

// Need to implement logic for the functions in the repository here:

    // 1. Function to add new Career Items
    fun addCareerItem(title: String, category: String, progressStatus: String, completionIndicator: String){
        val newCareerItem = CareerItem(title, category, progressStatus, completionIndicator)
        repository.addCareerItem(newCareerItem)
    }
}