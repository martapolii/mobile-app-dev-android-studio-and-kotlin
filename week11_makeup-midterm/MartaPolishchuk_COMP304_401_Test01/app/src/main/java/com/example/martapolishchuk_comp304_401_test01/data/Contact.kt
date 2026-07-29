package com.example.martapolishchuk_comp304_401_test01.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// contact data class

data class Contact (

    // text fields for ID (3 digits)
    val contactId: String,

    // name
    val name: String,

    // cell phone (10 digits)
    val cellPhone: String,

    // email
    val email: String,

    // contact type
    val contactType: String,

    // favourite
    val favourite: String
)

// contact type (drop down): Family, Personal, Relative
val contactTypeOptions = listOf(
    "Family",
    "Personal",
    "Relative"
)

// favourite (radio button): Yes, No
val favouriteOptions = listOf(
    "No",
    "Yes"
)


