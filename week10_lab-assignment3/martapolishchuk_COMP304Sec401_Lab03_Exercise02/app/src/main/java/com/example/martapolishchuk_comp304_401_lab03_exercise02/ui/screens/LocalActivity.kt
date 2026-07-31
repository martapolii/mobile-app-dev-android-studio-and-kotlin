package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import android.app.Activity
import androidx.compose.runtime.compositionLocalOf

val LocalActivity = compositionLocalOf<Activity> {
    error("LocalActivity not provided")
}