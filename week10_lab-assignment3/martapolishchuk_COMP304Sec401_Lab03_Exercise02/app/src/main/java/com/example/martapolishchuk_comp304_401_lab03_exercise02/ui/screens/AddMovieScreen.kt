package com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.screens

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.martapolishchuk_comp304_401_lab03_exercise02.ui.viewmodel.MovieViewModel
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreen (
    navController: NavController,
    viewModel: MovieViewModel = viewModel()
){

    val state = viewModel.addMovieState.collectAsState().value
    var showDatePicker by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val genres = listOf("Family", "Comedy", "Thriller", "Action", "Drama")

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

    // Screen Content with TopAppBar
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Movie") },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

                    // ID
                    OutlinedTextField(
                        value = state.id,
                        onValueChange = { viewModel.updateFormState(id = it) },
                        label = { Text("Movie ID") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Title
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = { viewModel.updateFormState(title = it) },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Director
                    OutlinedTextField(
                        value = state.director,
                        onValueChange = { viewModel.updateFormState(director = it) },
                        label = { Text("Director") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Price
                    OutlinedTextField(
                        value = state.price,
                        onValueChange = { viewModel.updateFormState(price = it) },
                        label = { Text("Price") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Release Date - date picker
                    OutlinedButton(
                        onClick = { showDatePicker = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(state.releaseDate.ifEmpty { "Select Release Date" })
                    }
                    Spacer(Modifier.height(16.dp))

                    // Duration (minutes)
                    OutlinedTextField(
                        value = state.duration,
                        onValueChange = { viewModel.updateFormState(duration = it) },
                        label = { Text("Duration (minutes)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Genre - drop down
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = state.genre,
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
                            genres.forEach { genre ->
                                DropdownMenuItem(
                                    text = { Text(genre) },
                                    onClick = {
                                        viewModel.updateFormState(genre = genre)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))

                    // Favorite toggle
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Favorite")
                        Spacer(Modifier.weight(1f))
                        Switch(
                            checked = state.isFavorite,
                            onCheckedChange = { viewModel.updateFormState(isFavorite = it) }
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // buttons row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // cancel button
                        Button(
                            onClick = {navController.popBackStack()}, //return to last visited screen
                        ) {
                            Text("Cancel")
                        }

                        // save button
                        val success by viewModel.addMovieSuccess.collectAsState()

                        Button(
                            onClick = { viewModel.validateAndAddMovie() },
                        ) {
                            Text("Save")
                        }

                        // Observe success and navigate
                        LaunchedEffect(success) {
                            if (success) {
                                navController.navigate("home") {
                                    popUpTo("add") { inclusive = true } // Corrected route name
                                }
                                viewModel.resetSuccessState()
                            }
                        }

                    }// row
                }// column
            }// card
        }
    } // scaffold
}