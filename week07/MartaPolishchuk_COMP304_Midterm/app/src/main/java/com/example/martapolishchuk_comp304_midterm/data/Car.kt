package com.example.martapolishchuk_comp304_midterm.data
// 301432299 - Marta Polishchuk

// Car data class - data model for the app
// data members
data class Car(
    val makeAndModel: String,
    val sellerName: String,
    val vehicleType: String,
    val manufacturingYear: Int,
    val sellingPrice: Double
)
// Form state object used by the form composable (car entry screen)
// keeping it as a separate objects lets you update the UI as the user adds inputs without changing the saved data model directly (prevents mutated data)
data class CarFormState(
    val makeAndModel: String = "",
    val sellerName: String = "",
    val vehicleType: String = "",
    val manufacturingYear: String = "",
    val sellingPrice: String = ""
)

// Segmented button options (Sedan, SUV, Hatchback)
val vehicleTypeOptions = listOf(
    "Sedan",
    "SUV",
    "Hatchback"
)

// Some pre-populated data for testing & demo purposes
val prepopulatedCars = listOf (
    Car(
        makeAndModel = "Toyota Camry",
        sellerName = "Nancy Longbottom",
        vehicleType = "Sedan",
        manufacturingYear = 2022,
        sellingPrice = 35000.0
    ),
    Car(
        makeAndModel = "Honda Accord",
        sellerName = "Tom Riddle",
        vehicleType = "Sedan",
        manufacturingYear = 2021,
        sellingPrice = 42000.0
    ),
    Car(
        makeAndModel = "Ford Mustang",
        sellerName = "Bellatrix Lestrange",
        vehicleType = "Sedan",
        manufacturingYear = 2020,
        sellingPrice = 68000.0
    )
)// prepopulatedCars end

// Helper to validate user input - checks if blank and that year is int and price is double
fun validateCarInput(
    makeAndModel: String,
    sellerName: String,
    vehicleType: String,
    manufacturingYear: String,
    sellingPrice: String
): String? {
    if (makeAndModel.isBlank()) {
        return "Please enter the Make and Model of the vehicle."
    }
    if (sellerName.isBlank()) {
        return "Please enter the Seller's name"
    }
    if (vehicleType.isBlank()) {
        return "Please select the Vehicle Type."
    }
    if (manufacturingYear.isBlank()) {
        return "Please enter Manufacturing year."
    }
    if (manufacturingYear.toIntOrNull() == null) {
        return "Please enter a valid manufacturing year."
    }
    if (sellingPrice.isBlank()){
        return "Please enter Selling Price."
    }
    if (sellingPrice.toDoubleOrNull() == null) {
        return "Please enter a valid Selling Price."
    }
    return null
}

