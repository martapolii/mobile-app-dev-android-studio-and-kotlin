package com.example.martapolishchuk_comp304lab2_ex1.screens.home

import androidx.lifecycle.ViewModel
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItem
import com.example.martapolishchuk_comp304lab2_ex1.data.CareerItemRepository
import kotlinx.coroutines.flow.StateFlow

// Marta Polishchuk - 301432299

class HomeViewModel(
    private val repository: CareerItemRepository
) : ViewModel() {

    // stateflow is in the rubric for observing ui data changes
    val careerItems: StateFlow<List<CareerItem>> = repository.getCareerItems()

    fun getCareerItemById(id: Int): CareerItem? {
        return repository.getCareerItemById(id)
    }
}
