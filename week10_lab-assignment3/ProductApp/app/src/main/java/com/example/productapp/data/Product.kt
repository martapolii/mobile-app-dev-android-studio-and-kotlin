package com.example.productapp.data
import androidx.room.PrimaryKey
import androidx.room.Entity

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 1 - Part 2 - Add 'quantity' field

// Represents a product entity in the Room database
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int, // every table must have a primary key
    val name: String,
    val price: Double,
    val quantity: Int, // added quantity property ***************************
    val deliveryDate: String,
    val category: String,
    val isFavorite: Boolean
)