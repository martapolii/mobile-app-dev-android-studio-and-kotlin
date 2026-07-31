package com.example.productapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.Product
import com.example.productapp.data.ProductDatabase
import com.example.productapp.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 1 - Part 2 - Add 'quantity' field

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>
    val favoriteProducts: LiveData<List<Product>>
    private val _addProductSuccess = MutableStateFlow(false)
    val addProductSuccess: StateFlow<Boolean> = _addProductSuccess.asStateFlow()

    // Form state handling
    data class AddProductState(
        val id: String = "",
        val name: String = "",
        val price: String = "",
        val quantity: String = "", // added quantity property **************************************
        val deliveryDate: String = "",
        val category: String = "",
        val isFavorite: Boolean = false,
        val errors: List<String> = emptyList()
    )

    private val _addProductState = MutableStateFlow(AddProductState())
    val addProductState: StateFlow<AddProductState> = _addProductState.asStateFlow()

    init {
        val dao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(dao)
        allProducts = repository.products.asLiveData()
        favoriteProducts = repository.favoriteProducts.asLiveData()
    }

    fun validateAndAddProduct() {
        val state = _addProductState.value
        val errors = mutableListOf<String>()

        // ID validation (3 digits, 101-999)
        val id = state.id.toIntOrNull()
        if (id == null || id !in 101..999) errors.add("Invalid ID (101-999)")

        // Price validation
        val price = state.price.toDoubleOrNull()
        if (price == null || price <= 0) errors.add("Price must be positive")

        // Quantity Validation ( quantity > 0 )*****************************************************
            // copied from ID validation
        val quantity = state.quantity.toIntOrNull()
        if (quantity == null || quantity !in 1..999) errors.add("Invalid Quantity (1-999)")

        // Date validation
        val currentDate = LocalDate.now()
        val deliveryDate = try {
            LocalDate.parse(state.deliveryDate)
        } catch (e: Exception) {
            null
        }
        if (deliveryDate == null || deliveryDate.isBefore(currentDate)) {
            errors.add("Invalid delivery date")
        }

        // Category validation
        if (state.category !in listOf("Electronics", "Appliances", "Cell Phone", "Media")) {
            errors.add("Select a category")
        }

        if (errors.isEmpty()) {
            insert(
                Product(
                    id = id!!,
                    name = state.name,
                    price = price!!,
                    quantity = quantity!!, // added quantity property ******************************
                    deliveryDate = state.deliveryDate,
                    category = state.category,
                    isFavorite = state.isFavorite
                )
            )
            _addProductState.update { it.copy(errors = emptyList()) }
            _addProductSuccess.value = true  // Set success to true
        } else {
            _addProductState.update { it.copy(errors = errors) }
            _addProductSuccess.value = false  // Reset success on validation failure
        }
    }

    // START ***************************************************************************************
    // created a separate function with validation logic ONLY, because the edit product page already has logic to update an existing product - doesn't need the insert product logic from above (also this is easier than trying to figure out how to make the above function work for the edit page (I did try)
    fun validateProduct(product: Product){ // need to be able to specify that a copy of a product object is being validated
        val state = _addProductState.value
        val errors = mutableListOf<String>()

        // Quantity Validation ( quantity > 0 )*****************************************************
        // copied from ID validation
        val quantity = product.quantity
        if (product.quantity !in 1..999) errors.add("Invalid Quantity (1-999)")
        // (only validating quantity as it's the only one relevant to the assignment)


        // if there are no errors, update the product state to successful -> user will be redirected to home page
        if (errors.isEmpty()) {
            viewModelScope.launch { // launching a coroutine to call the repo to update the product
                repository.updateProduct(product)
            }
            _addProductState.update { it.copy(errors = emptyList()) }
            _addProductSuccess.value = true  // Set success to true
        } else { // if there are errors, do not redirect user, success is set to false
            _addProductState.update { it.copy(errors = errors) }
            _addProductSuccess.value = false  // Reset success on validation failure
        }

    } // *************************************************************************************** END

    // Update form fields
    fun updateFormState(
        id: String? = null,
        name: String? = null,
        price: String? = null,
        quantity: String? = null, // added quantity property ***************************************
        deliveryDate: String? = null,
        category: String? = null,
        isFavorite: Boolean? = null
    ) {
        _addProductState.update { current ->
            current.copy(
                id = id ?: current.id,
                name = name ?: current.name,
                price = price ?: current.price,
                quantity = quantity ?: current.quantity, // added quantity property ****************
                deliveryDate = deliveryDate ?: current.deliveryDate,
                category = category ?: current.category,
                isFavorite = isFavorite ?: current.isFavorite
            )
        }
    }

    fun toggleFavorite(product: Product) {
        val updatedProduct = product.copy(isFavorite = !product.isFavorite)
        viewModelScope.launch {
            repository.updateProduct(updatedProduct)
        }
    }

    fun resetSuccessState() {
        _addProductSuccess.value = false
    }

    // CRUD operations
    private fun insert(product: Product) = viewModelScope.launch { repository.addProduct(product) }
    fun update(product: Product) = viewModelScope.launch { repository.updateProduct(product) }
    fun delete(product: Product) = viewModelScope.launch { repository.deleteProduct(product) }
}