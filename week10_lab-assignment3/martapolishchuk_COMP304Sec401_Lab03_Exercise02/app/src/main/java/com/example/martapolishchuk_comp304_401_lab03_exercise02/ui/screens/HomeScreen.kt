package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.martapolishchuk_comp304_401_lab03_exercise02.data.Movie
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.viewmodel.MovieViewModel

// main screen displaying all movies in database
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MovieViewModel = viewModel(),
    windowSizeClass: WindowSizeClass = calculateWindowSizeClass(LocalActivity.current)
) {
    val movies by viewModel.allMovies.observeAsState(emptyList())
    println("HomeScreen movies: $movies") // Log to verify data
    val isExpandedScreen = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movie DVD Manager") },
                actions = {
                    Row() { // row of buttons at top of app
                        OutlinedButton(onClick = { navController.navigate(route = "add") }) {
                            Text("Add Movie")
                        }
                        OutlinedButton(onClick = { navController.navigate(route = "favorites") }) {
                            Text("Favorites")
                        }
                        OutlinedButton(onClick = { navController.navigate(route = "home_detail") }) {
                            Text("Display All")
                        }
                    }

                }
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {

            if (isExpandedScreen) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) { // Movie List - Expanded Screens:
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(movies) { movie ->
                            MovieItem(
                                movie = movie,
                                onEdit = { navController.navigate("edit/${movie.id}") },
                                onDelete = { viewModel.delete(movie) },
                                onToggleFavorite = { viewModel.toggleFavorite(movie) },
                                modifier = Modifier
                                    .clickable { selectedMovie = movie }
                                    .background(
                                        if (movie == selectedMovie)
                                            MaterialTheme.colorScheme.secondaryContainer
                                        else Color.Transparent
                                    )
                            )
                        }
                    }

                    // Movie Details - Expanded Screens:
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        selectedMovie?.let { movie ->
                            Column {
                                Text(movie.title, style = MaterialTheme.typography.headlineMedium)
                                Spacer(Modifier.height(16.dp))
                                Text(movie.director, style = MaterialTheme.typography.headlineSmall)
                                Text(
                                    "Price: $${movie.price}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    "Release Date: ${movie.releaseDate}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Duration (minutes): ${movie.duration}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    "Genre: ${movie.genre}",
                                    style = MaterialTheme.typography.bodyLarge
                                )

                            }
                        } ?: Text(
                            "Select a movie",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                // MOVIE LIST - REGULAR SCREEN
            } else {
                LazyColumn(modifier = Modifier.padding(paddingValues)) {
                    items(movies) { movie ->
                        MovieItem(
                            movie = movie,
                            onEdit = { navController.navigate("edit/${movie.id}") },
                            onDelete = { viewModel.delete(movie) },
                            onToggleFavorite = { viewModel.toggleFavorite(movie) },
                            modifier = Modifier
                                .background(
                                    if (movie == selectedMovie)
                                        MaterialTheme.colorScheme.secondaryContainer
                                    else Color.Transparent
                                )
                        )
                    }
                } // lazy column
            } // reg. screen
        } // column
    } // scaffold
} // home screen
