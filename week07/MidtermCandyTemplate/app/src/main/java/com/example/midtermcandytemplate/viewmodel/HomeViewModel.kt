package com.example.midtermcandytemplate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.model.Candy
import com.example.midtermcandytemplate.model.CandyRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * Home screen ViewModel.
 *
 * Its job is simple: expose the candy list from the repository to the home activity.
 */
class HomeViewModel(
    repository: CandyRepository
) : ViewModel() {
    val candies: StateFlow<List<Candy>> = repository.getCandies()
}

/**
 * Factory keeps the week 5/6 constructor injection pattern in place.
 */
class HomeViewModelFactory(
    private val repository: CandyRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
