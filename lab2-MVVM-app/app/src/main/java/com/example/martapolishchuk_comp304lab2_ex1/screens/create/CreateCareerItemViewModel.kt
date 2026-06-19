package com.example.martapolishchuk_comp304lab2_ex1.screens.create

import androidx.lifecycle.ViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository

class CreateCareerItemViewModel (private val repository: CareerItemRepository) : ViewModel() {

   // Business Logic:

    // 1. Function to add new Career Items
    fun addCareerItem(
        title: String,
        description: String,
        category: String,
        startDate: String,
        targetCompletionDate: String,
        status: String,
        progressPercentage: Int
    ){
        val newCareerItem = CareerItem(
            id = 0,
            title = title,
            description = description,
            category = category,
            startDate = startDate,
            targetCompletionDate = targetCompletionDate,
            status = status,
            progressPercentage = progressPercentage
        )
        repository.addCareerItem(newCareerItem)
    }
}
