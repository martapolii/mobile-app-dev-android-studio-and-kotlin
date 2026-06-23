package com.vinay.retrofitjsonapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * The UI reads everything it needs from one state object.
 *
 * This makes the screen easier to reason about because loading, error, and data
 * all live in one place instead of being scattered across several variables.
 */
data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

/**
 * The ViewModel owns screen state and survives configuration changes.
 *
 * Week 6 focuses on coroutines and network calls, so this class is where we launch
 * the Retrofit request and publish the result through StateFlow.
 */
class PostViewModel : ViewModel() {
    // Mutable inside the ViewModel, read-only outside the ViewModel.
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        // Start loading as soon as the screen's ViewModel is created.
        loadPosts()
    }

    /**
     * Launches a coroutine tied to the ViewModel lifecycle.
     *
     * `viewModelScope` is cancelled automatically when the ViewModel is cleared,
     * which is why it is the safe place to run the network request.
     */
    fun loadPosts() {
        // Reset the state before each request so the UI can show a loading indicator.
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                // Suspend here while Retrofit fetches and Gson parses the JSON.
                val downloadedPosts = RetrofitClient.api.getPosts()

                // Publish a brand-new state object so Compose can redraw the screen.
                _uiState.value = PostUiState(
                    posts = downloadedPosts,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (exception: Exception) {
                // In a production app we might log the exception or map specific failures.
                _uiState.value = PostUiState(
                    posts = emptyList(),
                    isLoading = false,
                    errorMessage = "Unable to load posts. Check your connection and try again."
                )
            }
        }
    }
}
