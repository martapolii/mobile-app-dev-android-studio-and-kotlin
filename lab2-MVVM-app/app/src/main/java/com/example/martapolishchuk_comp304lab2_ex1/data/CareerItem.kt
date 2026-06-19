package com.example.martapolishchuk_comp304lab2_ex1.data

data class CareerItem(
    // data members used across the 3 screens
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val startDate: String,
    val targetCompletionDate: String,
    val status: String,
    val progressPercentage: Int
) {
    val completionIndicator: Boolean
        get() = status == "Completed"
}

val careerCategories = listOf(
    "Skill Development",
    "Certification",
    "Academic Project",
    "Internship Application",
    "Career Goal"
)

val careerStatuses = listOf(
    "Not Started",
    "In Progress",
    "Completed"
)
