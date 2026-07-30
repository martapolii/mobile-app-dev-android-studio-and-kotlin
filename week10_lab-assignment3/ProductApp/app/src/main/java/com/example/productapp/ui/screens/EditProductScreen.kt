// Marta Polishchuk - 301432299
// Assignment 3: Exercise 1 - Part 2 - Add 'quantity' field

package com.example.productapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.productapp.ui.viewmodel.ProductViewModel

// Screen for editing an existing product
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navController: NavController,
    productId: Int?,
    viewModel: ProductViewModel = viewModel()
) {
    val products by viewModel.allProducts.observeAsState(emptyList())
    println("EditProductScreen: productId=$productId, products=$products") // Log for debugging

    if (productId == null) {
        LaunchedEffect(Unit) { navController.popBackStack() }
        return
    }

    val product = products.find { it.id == productId }
    if (product == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator() // Show loading while waiting for product
        }
        return
    }

    var editedName by remember { mutableStateOf(product.name) }
    var editedPrice by remember { mutableStateOf(product.price.toString()) }
    // added variable to store updated quantity: *************************************************
    var editedQuantity by remember { mutableStateOf(product.quantity.toString()) } // converted value to a string for editing
    var editedCategory by remember { mutableStateOf(product.category) }
    var editedFavorite by remember { mutableStateOf(product.isFavorite) }
    var expanded by remember { mutableStateOf(false) } // State for dropdown expansion
    val categories = listOf("Electronics", "Appliances", "Cell Phone", "Media")

    val state = viewModel.addProductState.collectAsState().value // needed this for state.errors to work (see card below)

    Column(modifier = Modifier.padding(16.dp)) {
        // copied this error card from 'add product screen', for quantity validation when editing products
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

        OutlinedTextField(
            value = editedName,
            onValueChange = { editedName = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = editedPrice,
            onValueChange = { editedPrice = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        // Added an edit Quantity field: ********************************************************************
        OutlinedTextField(
            value = editedQuantity,
            onValueChange = {
                editedQuantity = it // update local state as user adds input
                viewModel.updateFormState(quantity = it ) }, //since I want to be able to validate this field, I need to update the value stored in the view model
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Category Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = editedCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Category Dropdown"
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
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            editedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }

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
            // delete button
            Button(
                onClick = {
                    viewModel.delete(product)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete")
            }

            // Submit Button
            val success by viewModel.addProductSuccess.collectAsState() // copied from add product screen

            // save button
            Button(onClick = {

                // create a copy of the object with the new values
                val updatedProduct = product.copy(
                    name = editedName,
                    price = editedPrice.toDoubleOrNull() ?: 0.0,
                    // added quantity so it updates when button is clicked: ****************************************
                    quantity = editedQuantity.toInt(), // convert back to int bc quantity is stored as int
                    category = editedCategory,
                    isFavorite = editedFavorite
                )
                // validate the inputs - NOT WORKING CORRECTLY
                viewModel.validateProduct(updatedProduct) // calling a custom validation function for this screen (in View model), and passing the updated product object as the argument



            }) {
                Icon(Icons.Default.Edit, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save")
            }// button

            // Copied from add product screen, so that you are redirected to the main page ONLY if the inputs were all valid ************************************
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
}//outer column