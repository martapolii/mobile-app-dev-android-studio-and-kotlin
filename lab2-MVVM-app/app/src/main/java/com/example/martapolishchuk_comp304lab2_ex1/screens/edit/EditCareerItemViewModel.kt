package com.example.martapolishchuk_comp304lab2_ex1.screens.edit

import androidx.lifecycle.ViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository

class EditCareerItemViewModel(
    private val repository: CareerItemRepository
) : ViewModel() {

    fun getCareerItemById(id: Int): CareerItem? {
        return repository.getCareerItemById(id)
    }

    fun updateCareerItem(
        id: Int,
        title: String,
        description: String,
        category: String,
        startDate: String,
        targetCompletionDate: String,
        status: String,
        progressPercentage: Int
    ) {
        repository.updateCareerItem(
            CareerItem(
                id = id,
                title = title,
                description = description,
                category = category,
                startDate = startDate,
                targetCompletionDate = targetCompletionDate,
                status = status,
                progressPercentage = progressPercentage
            )
        )
    }
}
