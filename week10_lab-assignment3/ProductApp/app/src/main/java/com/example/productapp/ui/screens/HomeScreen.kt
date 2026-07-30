package com.example.productapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
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
import com.example.productapp.data.Product
import com.example.productapp.ui.viewmodel.ProductViewModel

// Main screen displaying all products
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel(),
    windowSizeClass: WindowSizeClass = calculateWindowSizeClass(LocalActivity.current)
) {
    val products by viewModel.allProducts.observeAsState(emptyList())
    println("HomeScreen products: $products") // Log to verify data
    val isExpandedScreen = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    Scaffold(
// TOP APP BAR W FAV + ADD ICON BUTTONS
        topBar = {
            TopAppBar(
                title = { Text("Product List") },
                actions = {
                    IconButton(onClick = { navController.navigate("favorites") }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                    }
                    IconButton(onClick = { navController.navigate("add") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Product")
                    }
                }
            )
        }
    ) { paddingValues ->

// PRODUCT LIST - EXPANDED SCREEN
        if (isExpandedScreen) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(products) { product ->
                        ProductItem(
                            product = product,
                            onEdit = {
                                navController.navigate("edit/${product.id}")
                            },
                            onDelete = { viewModel.delete(product) },
                            onToggleFavorite = { viewModel.toggleFavorite(product) },
                            modifier = Modifier
                                .clickable { selectedProduct = product }
                                .background(
                                    if (product == selectedProduct)
                                        MaterialTheme.colorScheme.secondaryContainer
                                    else Color.Transparent
                                )
                        )
                    }
                } // lazy column

                // Below box only shows on expanded screens - tested with pixel 10 pro fold emulator - product list & product view screen are displayed side-by-side
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    selectedProduct?.let { product ->
                        Column {
                            Text(product.name, style = MaterialTheme.typography.headlineMedium)
                            Spacer(Modifier.height(16.dp))
                            Text("Price: $${product.price}", style = MaterialTheme.typography.titleMedium)
                            Text("Category: ${product.category}", style = MaterialTheme.typography.bodyLarge)
                            Text("Delivery Date: ${product.deliveryDate}", style = MaterialTheme.typography.bodyMedium)
                        }
                    } ?: Text(
                        "Select a product",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyLarge
                    )
                } // box
            } // expanded screen

// PRODUCT LIST - REGULAR SCREEN
        } else {
            // inserting search bar here - above the lazy column, bellow the top app bar




            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onEdit = { navController.navigate("edit/${product.id}") },
                        onDelete = { viewModel.delete(product) },
                        onToggleFavorite = { viewModel.toggleFavorite(product) },
                        modifier = Modifier
                            .background(
                                if (product == selectedProduct)
                                    MaterialTheme.colorScheme.secondaryContainer
                                else Color.Transparent
                            )
                    )
                }
            } // lazy column
        } // reg. screen
    } // scaffold
} // home screen

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 1 - add a Search text box to search for a product based on Product ID

// SEARCH BAR COMPOSABLE
// Code used from: https://developer.android.com/develop/ui/compose/components/search-bar?authuser=1, example 1: "Search bar with suggestions"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    // Controls expansion state of the search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true } // for accessibility (screen readers)
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },// for accessibility (screen readers)

// SEARCH BAR ----------------------
            inputField = {
                SearchBarDefaults.InputField( // creates input field + handles changes to the query
                    query = textFieldState.text.toString(), // query text to be shown in the input field
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } }, // lambda to handle changes in the query string (text input and updates state when input changes)
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false // default = not expanded to show suggestions list
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it }, // lambda to handle changes in dropdown when expanded
                    placeholder = { Text("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {

 // SEARCH RESULTS -----------------
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result -> // iterates through search result list & creates a list item for each result
                    ListItem(
                        headlineContent = { Text(result) }, // content to be displayed
                        modifier = Modifier
                            .clickable { // when a list item is clicked, the text field is updated, suggestions list collapses, and text field (search bar) is filled with the selected result
                                textFieldState.edit { replace(0, length, result) }
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
} // simple search bar