package com.example.martapolishchuk_comp304lab2_ex1.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

data class CareerItem(
    // data members - need: title, category, progress status, completion indicator
    val title: String,
    val category: String, //["Skill Development", "Certification", "Academic Project", "Internship Application", "Career Goal"]
    val progressStatus: String,
    val completionIndicator: Boolean


)
// sample data
var careerItemList = mutableStateListOf(
    CareerItem("COMP304 Lab", "Centennial College", "2026-06-09", true),
    CareerItem("COMP304 Lab", "Centennial College", "2026-06-09", false),
    CareerItem("COMP304 Lab", "Centennial College", "2026-06-09", false)
)

