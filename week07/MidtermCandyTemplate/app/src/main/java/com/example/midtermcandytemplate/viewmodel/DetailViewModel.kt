package com.example.midtermcandytemplate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.midtermcandytemplate.model.CandyFormState
import com.example.midtermcandytemplate.model.CandyRepository
import com.example.midtermcandytemplate.model.DetailScreenMode
import com.example.midtermcandytemplate.model.toCandy
import com.example.midtermcandytemplate.model.toFormState
import com.example.midtermcandytemplate.model.validateCandyInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Screen state for the detail activity.
 */
data class DetailUiState(
    val formState: CandyFormState = CandyFormState(),
    val screenMode: DetailScreenMode = DetailScreenMode.EDIT,
    val receivedCandyId: Int = -1,
    val validationMessage: String? = null
)

/**
 * Detail ViewModel handles loading one item, editing it, and saving it.
 */
class DetailViewModel(
    private val repository: CandyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    /**
     * Loads the selected candy into editable form state.
     */
    fun loadCandy(
        candyId: Int,
        screenMode: DetailScreenMode
    ) {
        val selectedCandy = repository.getCandyById(candyId)

        _uiState.value = if (selectedCandy != null) {
            DetailUiState(
                formState = selectedCandy.toFormState(),
                screenMode = screenMode,
                receivedCandyId = candyId,
                validationMessage = null
            )
        } else {
            DetailUiState(
                formState = CandyFormState(),
                screenMode = screenMode,
                receivedCandyId = candyId,
                validationMessage = "The selected candy could not be found."
            )
        }
    }

    fun onNameChange(newValue: String) = updateForm { it.copy(name = newValue) }
    fun onTypeChange(newValue: String) = updateForm { it.copy(type = newValue) }
    fun onSugarFreeChange(newValue: Boolean) = updateForm { it.copy(isSugarFree = newValue) }
    fun onFlavorProfileChange(newValue: String) = updateForm { it.copy(flavorProfile = newValue) }
    fun onDescriptionChange(newValue: String) = updateForm { it.copy(description = newValue) }
    fun onFavoriteToggle() = updateForm { currentState ->
        currentState.copy(isFavorite = !currentState.isFavorite)
    }

    /**
     * Saves the edited candy back into the repository.
     *
     * Returning Boolean makes the activity decide whether it should finish and go back.
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

        repository.updateCandy(currentState.formState.toCandy())
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
 * Factory for the detail screen constructor injection used in the week 5 MVVM examples.
 */
class DetailViewModelFactory(
    private val repository: CandyRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
