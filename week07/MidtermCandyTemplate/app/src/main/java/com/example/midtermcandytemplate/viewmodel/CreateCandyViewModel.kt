package com.example.midtermcandytemplate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.model.CandyFormState
import com.example.midtermcandytemplate.model.CandyRepository
import com.example.midtermcandytemplate.model.toCandy
import com.example.midtermcandytemplate.model.validateCandyInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Screen state for the optional create activity.
 */
data class CreateCandyUiState(
    val formState: CandyFormState = CandyFormState(),
    val validationMessage: String? = null
)

/**
 * Create ViewModel uses the same form ideas as the detail screen, but saves a new item.
 */
class CreateCandyViewModel(
    private val repository: CandyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateCandyUiState())
    val uiState: StateFlow<CreateCandyUiState> = _uiState.asStateFlow()

    fun onNameChange(newValue: String) = updateForm { it.copy(name = newValue) }

    fun onTypeChange(newValue: String) = updateForm { it.copy(type = newValue) }

    fun onSugarFreeChange(newValue: Boolean) = updateForm { it.copy(isSugarFree = newValue) }

    fun onFlavorProfileChange(newValue: String) = updateForm { it.copy(flavorProfile = newValue) }

    fun onDescriptionChange(newValue: String) = updateForm { it.copy(description = newValue) }

    fun onFavoriteToggle() = updateForm { currentState ->
        currentState.copy(isFavorite = !currentState.isFavorite)
    }

    /**
     * Adds a new candy item to the shared repository.
     */
    fun saveCandy(): Boolean {
        val currentState = _uiState.value
        val validationMessage = validateCandyInput(
            name = currentState.formState.name,
            description = currentState.formState.description
        )

        if (validationMessage != null) {
            _uiState.value = currentState.copy(validationMessage = validationMessage)
            return false
        }

        repository.addCandy(currentState.formState.toCandy(idOverride = -1))
        _uiState.value = currentState.copy(validationMessage = null)
        return true
    }

    private fun updateForm(
        transform: (CandyFormState) -> CandyFormState
    ) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            formState = transform(currentState.formState),
            validationMessage = null
        )
    }
}

/**
 * Factory for the optional create activity constructor injection used in the week 5 MVVM examples.
 */
class CreateCandyViewModelFactory(
    private val repository: CandyRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCandyViewModel::class.java)) {
            return CreateCandyViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
