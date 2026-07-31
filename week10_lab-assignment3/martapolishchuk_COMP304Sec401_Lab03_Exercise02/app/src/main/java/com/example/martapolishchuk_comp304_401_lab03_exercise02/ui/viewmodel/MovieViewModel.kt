package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.viewmodel

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.martapolishchuk_comp304_401_lab03_exercise02.data.Movie
import com.example.martapolishchuk_comp304_401_lab03_exercise02.data.MovieDatabase
import com.example.martapolishchuk_comp304_401_lab03_exercise02.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository
    val allMovies: LiveData<List<Movie>>
    val favoriteMovies: LiveData<List<Movie>>
    private val _addMovieSuccess = MutableStateFlow(false)
    val addMovieSuccess: StateFlow<Boolean> = _addMovieSuccess.asStateFlow()

    // Form state handling
    data class AddMovieState(
        val id: String = "",
        val title: String = "",
        val director: String = "",
        val price: String = "",
        val releaseDate: String = "",
        val duration: String = "",
        val genre: String = "",
        val isFavorite: Boolean = false,
        val errors: List<String> = emptyList()
    )

    private val _addMovieState = MutableStateFlow(AddMovieState())
    val addMovieState: StateFlow<AddMovieState> = _addMovieState.asStateFlow()

    init {
        val dao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(dao)
        allMovies = repository.movies.asLiveData()
        favoriteMovies = repository.favoriteMovies.asLiveData()
    }

    // validation for add new movie screen
    fun validateAndAddMovie() {
        val state = _addMovieState.value
        val errors = mutableListOf<String>()

        // ID validation (3 digits, 101-999)
        val id = state.id.toIntOrNull()
        if (id == null || id !in 101..999) errors.add("Invalid ID (101-999)")

        // title
        val title = state.title
        if ( title.isEmpty() ) errors.add("Enter movie title")

        // director
        val director = state.director
        if ( director.isEmpty() ) errors.add("Enter Director name")

        // Price of DVD
        val price = state.price.toDoubleOrNull()
        if (price == null || price <= 0) errors.add("Price must be positive")

        // release date validation
        val currentDate = LocalDate.now()
        val releaseDate = try {
            LocalDate.parse(state.releaseDate)
        } catch (e: Exception) {
            null
        }
        if (releaseDate == null || releaseDate.isBefore(currentDate)) {
            errors.add("Invalid release date")
        }

        // duration validation (can not be 0 or negative)
        val duration = state.duration.toIntOrNull()
        if (duration == null || duration !in 1..500) errors.add("Invalid Quantity (1-500)")

        // genre validation
        if (state.genre !in listOf("Family", "Comedy", "Thriller", "Action", "Drama")) {
            errors.add("Select a genre")
        }

        if (errors.isEmpty()) {
            insert(
                Movie(
                    id = id!!,
                    title = state.title,
                    director = state.director,
                    price = price!!,
                    releaseDate = state.releaseDate,
                    duration = duration!!,
                    genre = state.genre,
                    isFavorite = state.isFavorite
                )
            )
            _addMovieState.update { it.copy(errors = emptyList()) }
            _addMovieSuccess.value = true  // Set success to true
        } else {
            _addMovieState.update { it.copy(errors = errors) }
            _addMovieSuccess.value = false  // Reset success on validation failure
        }
    }

    // validation for edit movie screen
    fun validateMovie(movie: Movie){ // need to be able to specify that a copy of a movie object is being validated
        val errors = mutableListOf<String>()

        // ID validation (3 digits, 101-999)
        val id = movie.id
        if (id !in 101..999) errors.add("Invalid ID (101-999)")

        // title
        val title = movie.title
        if ( title.isEmpty() ) errors.add("Enter movie title")

        // director
        val director = movie.director
        if ( director.isEmpty()) errors.add("Enter Director name")

        // Price of DVD
        val price = movie.price
        if ( price <= 0) errors.add("Price must be positive")

        // release date validation
        val currentDate = LocalDate.now()
        val releaseDate = try {
            LocalDate.parse(movie.releaseDate)
        } catch (e: Exception) {
            null
        }
        if (releaseDate == null ) {
            errors.add("Invalid release date")
        }

        // duration validation (can not be 0 or negative)
        val duration = movie.duration
        if ( duration !in 1..500) errors.add("Invalid duration (1-500)")

        // genre validation
        if (movie.genre !in listOf("Family", "Comedy", "Thriller", "Action", "Drama")) {
            errors.add("Select a genre")
        }

        // if there are no errors, update the movie state to successful -> user will be redirected to home page
        if (errors.isEmpty()) {
            viewModelScope.launch { // launching a coroutine to call the repo to update the movie
                repository.updateMovie(movie)
            }
            _addMovieState.update { it.copy(errors = emptyList()) }
            _addMovieSuccess.value = true  // Set success to true
        } else { // if there are errors, do not redirect user, success is set to false
            _addMovieState.update { it.copy(errors = errors) }
            _addMovieSuccess.value = false  // Reset success on validation failure
        }

    }

    // Update form fields
    fun updateFormState(
        id: String? = null,
        title: String? = null,
        director: String? = null,
        price: String? = null,
        releaseDate: String? = null,
        duration: String? = null,
        genre: String? = null,
        isFavorite: Boolean? = null
    ) {
        _addMovieState.update { current ->
            current.copy(
                id = id ?: current.id,
                title = title ?: current.title,
                director = director ?: current.director,
                price = price ?: current.price,
                releaseDate = releaseDate ?: current.releaseDate,
                duration = duration ?: current.duration,
                genre = genre ?: current.genre,
                isFavorite = isFavorite ?: current.isFavorite
            )
        }
    }

    fun toggleFavorite(movie: Movie) {
        val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
        viewModelScope.launch {
            repository.updateMovie(updatedMovie)
        }
    }

    fun resetSuccessState() {
        _addMovieSuccess.value = false
    }

    // CRUD operations
    private fun insert(movie: Movie) = viewModelScope.launch { repository.addMovie(movie) }
    fun update(movie: Movie) = viewModelScope.launch { repository.updateMovie(movie) }
    fun delete(movie: Movie) = viewModelScope.launch { repository.deleteMovie(movie) }
}




