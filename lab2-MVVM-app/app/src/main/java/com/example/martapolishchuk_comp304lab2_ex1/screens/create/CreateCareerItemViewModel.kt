package com.example.martapolishchuk_comp304lab2_ex1.screens.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository

class CreateCareerItemViewModel (private val repository: CareerItemRepository) : ViewModel() {

    // LiveData to observe changes in the list of Career Items
    val careerItems: LiveData<List<CareerItem>> = repository.getCareerItems()

   // Business Logic:

    // 1. Function to add new Career Items
    fun addCareerItem(title: String, category: String, progressStatus: String, completionIndicator: Boolean){
        val newCareerItem = CareerItem(title, category, progressStatus, completionIndicator)
        repository.addCareerItem(newCareerItem)
    }
}