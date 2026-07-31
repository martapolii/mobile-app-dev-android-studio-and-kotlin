package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.viewmodel.MovieViewModel
import java.time.Instant
import java.time.ZoneId

// screen for editing an existing Movie
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMovieScreen (
    navController: NavController,
    movieId: Int?,
    viewModel: MovieViewModel = viewModel()
){
    val movies by viewModel.allMovies.observeAsState(emptyList())
    println("EditMovieScreen: movieId=$movieId, movies=$movies") // Log for debugging

    if (movieId == null) {
        LaunchedEffect(Unit) { navController.popBackStack() }
        return
    }

    val movie = movies.find { it.id == movieId }
    if (movie == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator() // Show loading while waiting for movie
        }
        return
    }

    var editedTitle by remember { mutableStateOf(movie.title) }
    var editedDirector by remember { mutableStateOf(movie.director) }
    var editedPrice by remember { mutableStateOf(movie.price.toString()) }
    var editedReleaseDate by remember { mutableStateOf(movie.releaseDate) }
    var editedDuration by remember { mutableStateOf(movie.duration.toString()) }
    var expanded by remember { mutableStateOf(false) } // State for dropdown expansion
    var editedGenre by remember {mutableStateOf(movie.genre)}
    val genres = listOf("Family", "Comedy", "Thriller", "Action", "Drama")
    var editedFavorite by remember { mutableStateOf(movie.isFavorite) }

    val state = viewModel.addMovieState.collectAsState().value // needed this for state.errors to work (see card below)
    var showDatePicker by remember { mutableStateOf(false) }

    // Date Picker Dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = System.currentTimeMillis(),
            yearRange = 2025..2030
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val date = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .toString()
                            viewModel.updateFormState(releaseDate = date)
                        }
                        showDatePicker = false
                    }
                ) { Text("OK") }
            }
        ) { DatePicker(state = datePickerState) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // card to show errors upon validation:
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Display errors
                state.errors.forEach { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            } // column
        }// card

        // edited title
        OutlinedTextField(
            value = editedTitle,
            onValueChange = { editedTitle = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        // edited director
        OutlinedTextField(
            value = editedDirector,
            onValueChange = { editedDirector = it },
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth()
        )

        // edited price
        OutlinedTextField(
            value = editedPrice,
            onValueChange = { editedPrice = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        // edited release date
        OutlinedButton(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(state.releaseDate.ifEmpty { "Select Release Date" })
        }
        Spacer(Modifier.height(16.dp))

        // edited duration
        OutlinedTextField(
            value = editedDuration,
            onValueChange = {
                editedDuration = it // update local state as user adds input
                viewModel.updateFormState(duration = it ) },
            label = { Text("Duration(minutes)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // edited genre
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = editedGenre,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Genre") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                genres.forEach { movie ->
                    DropdownMenuItem(
                        text = { Text(movie) },
                        onClick = {
                            editedGenre = movie
                            expanded = false
                        }
                    )
                }
            }
        }

        // edited isFavorite
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Favorite:")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = editedFavorite, onCheckedChange = { editedFavorite = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // added a cancel button
            Button(
                onClick = {
                    navController.popBackStack() // just want to redirect on click, not update anything
                },
            ){
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cancel")
            }

            // boolean for the state of the form when submitted - if false then that means there were errors - pass this to function at the bottom of page to determine whether user should be redirected to the home page, or remain on the edit page
            val success by viewModel.addMovieSuccess.collectAsState()

            // save button
            Button(onClick = {
                // create a copy of the object with the new values
                val updatedMovie = movie.copy(
                    title = editedTitle,
                    director = editedDirector,
                    price = editedPrice.toDoubleOrNull() ?: 0.0,
                    releaseDate = editedReleaseDate,
                    duration = editedDuration.toIntOrNull() ?: 0,  // convert back to int bc duration is stored as int
                    genre = editedGenre,
                    isFavorite = editedFavorite
                )
                // calling a custom validation function for this screen (in View model), and passing the updated movie object as the argument
                viewModel.validateMovie(updatedMovie)

            }) {
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save")
            }// button

            //  you are redirected to the main page ONLY if the inputs were all valid:
            LaunchedEffect(success) {
                if (success) {
                    navController.navigate("home") {
                        popUpTo("add") { inclusive = true } // Corrected route name
                    }
                    viewModel.resetSuccessState()
                }
            }
        } // row
    }// inner column

}//edit movie screen