package com.example.martapolishchuk_COMP304Sec401_Lab03_Exercise1.ui.screens

// LocalActivity.kt
import android.app.Activity
import androidx.compose.runtime.compositionLocalOf

val LocalActivity = compositionLocalOf<Activity> {
    error("LocalActivity not provided")
}
