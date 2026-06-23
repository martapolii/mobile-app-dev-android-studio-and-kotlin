package com.example.midtermcandytemplate.model

/**
 * Main data model for the template.
 *
 * The fields stay intentionally simple so you can swap "candy" for any other
 * topic
 */
data class Candy(
    val id: Int,
    val name: String,
    val type: String,
    val isSugarFree: Boolean,
    val flavorProfile: String,
    val description: String,
    val isFavorite: Boolean
)

/**
 * Form state used by the create/edit screens.
 *
 * Keeping a separate form state object makes it easy to update the UI as the user
 * types without mutating the saved data model directly.
 */
data class CandyFormState(
    val id: Int = -1,
    val name: String = "",
    val type: String = candyTypeOptions.first(),
    val isSugarFree: Boolean = false,
    val flavorProfile: String = candyFlavorProfileOptions.first(),
    val description: String = "",
    val isFavorite: Boolean = false
)

/**
 * Screen mode lets the detail activity behave like a view screen or an edit screen.
 */
enum class DetailScreenMode {
    VIEW,
    EDIT,
    CREATE
}

/**
 * Radio button options 
 */
val candyTypeOptions = listOf(
    "Chocolate",
    "Gummy",
    "Hard Candy"
)

/**
 * Segmented button options 
 */
val candyFlavorProfileOptions = listOf(
    "Classic",
    "Sour",
    "Fruity"
)

/**
 * A few pre-populated items to make the LazyColumn useful when starting
 */
val starterCandies = listOf(
    Candy(
        id = 1,
        name = "Berry Burst Gummies",
        type = "Gummy",
        isSugarFree = false,
        flavorProfile = "Fruity",
        description = "Soft gummy candies with a bright mixed-berry flavor.",
        isFavorite = true
    ),
    Candy(
        id = 2,
        name = "Mint Chocolate Squares",
        type = "Chocolate",
        isSugarFree = true,
        flavorProfile = "Classic",
        description = "Chocolate squares with a cool mint center.",
        isFavorite = false
    ),
    Candy(
        id = 3,
        name = "Lemon Spark Drops",
        type = "Hard Candy",
        isSugarFree = false,
        flavorProfile = "Sour",
        description = "Hard candies with a tangy lemon finish.",
        isFavorite = false
    )
)

/**
 * Converts a saved Candy object into editable form state
 */
fun Candy.toFormState(): CandyFormState {
    return CandyFormState(
        id = id,
        name = name,
        type = type,
        isSugarFree = isSugarFree,
        flavorProfile = flavorProfile,
        description = description,
        isFavorite = isFavorite
    )
}

/**
 * Converts editable form state back into a saved Candy object
 */
fun CandyFormState.toCandy(idOverride: Int = id): Candy {
    return Candy(
        id = idOverride,
        name = name.trim(),
        type = type,
        isSugarFree = isSugarFree,
        flavorProfile = flavorProfile,
        description = description.trim(),
        isFavorite = isFavorite
    )
}

/**
 * Small validation helper for create and edit flows
 */
fun validateCandyInput(
    name: String,
    description: String
): String? {
    if (name.isBlank()) {
        return "Please enter a candy name."
    }

    if (description.isBlank()) {
        return "Please enter a short candy description."
    }

    return null
}
