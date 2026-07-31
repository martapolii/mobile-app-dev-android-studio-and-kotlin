package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun GenreDropdown (
    selectedGenre: String,
    onGenreSelected: (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    val genres = listOf("Family", "Comedy", "Thriller", "Action", "Drama")

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedGenre,
            onValueChange = {},
            label = { Text("Select Genre") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Genre Dropdown"
                )
            },
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            genres.forEach { genre ->
                DropdownMenuItem(
                    text = { Text(genre) },
                    onClick = {
                        onGenreSelected(genre)
                        expanded = false
                    }
                )
            }
        }
    }
}