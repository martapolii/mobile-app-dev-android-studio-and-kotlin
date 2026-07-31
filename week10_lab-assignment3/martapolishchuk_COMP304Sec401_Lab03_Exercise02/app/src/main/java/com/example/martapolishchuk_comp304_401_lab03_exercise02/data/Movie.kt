package com.example.martapolishchuk_comp304_401_lab03_exercise02.data

import androidx.room.PrimaryKey
import androidx.room.Entity

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

// Represents a movie entity in the Room database
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int, // every table must have a primary key
    val title: String,
    val director: String,
    val price: Double,
    val releaseDate: String,
    val duration: Int,
    val genre: String,
    val isFavorite: Boolean
)






