package com.example.martapolishchuk_comp304_401_lab03_exercise02.data

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import kotlinx.coroutines.flow.Flow
import kotlin.text.insert

class MovieRepository(private val dao: MovieDao) {
    val movies: Flow<List<Movie>> = dao.getAll()  // Stream of all movies
    val favoriteMovies: Flow<List<Movie>> = dao.getFavorites() // Stream of favorite movies

    suspend fun addMovie(movie: Movie) { dao.insert(movie) }    // Adds a new movie
    suspend fun updateMovie(movie: Movie) { dao.update(movie) } // Updates an existing movie
    suspend fun deleteMovie(movie: Movie) { dao.delete(movie) } // Deletes movie

    // Inserts initial sample movies
    suspend fun insertSampleMovies(movies: List<Movie>) {
        dao.insertSampleMovies(movies)
    }
}