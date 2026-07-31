package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.martapolishchuk_comp304_401_lab03_exercise02.data.Movie

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

@Composable
fun MovieItem(
    movie: Movie,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Movie info column without clickable modifier
            Column(modifier = Modifier.weight(1f)) {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                Text(movie.director, style = MaterialTheme.typography.titleSmall)
                Text("${movie.price}", style = MaterialTheme.typography.bodyMedium)
                Text(movie.releaseDate, style = MaterialTheme.typography.bodyMedium)
                Text("${movie.duration}", style = MaterialTheme.typography.bodyMedium)
                Text(movie.genre, style = MaterialTheme.typography.bodySmall)
                Text("${movie.isFavorite}", style = MaterialTheme.typography.bodySmall)
            }

            // Action buttons
            Row {
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.testTag("favorite_button")
                ) {
                    Icon(
                        imageVector = if (movie.isFavorite) Icons.Default.Favorite
                        else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (movie.isFavorite) Color.Red
                        else MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(
                    onClick = {
                        println("Edit clicked for movie ${movie.id}")
                        onEdit()
                    },
                    modifier = Modifier.testTag("edit_button")
                ) {
                    Icon(Icons.Default.Edit, "Edit")
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.testTag("delete_button")
                ) {
                    Icon(Icons.Default.Delete, "Delete")
                }
            }
        }
    }
}


