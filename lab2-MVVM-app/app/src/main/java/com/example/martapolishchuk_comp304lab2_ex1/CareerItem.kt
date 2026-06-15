package com.example.martapolishchuk_comp304lab2_ex1

// Career Item class - this will be the data/model class which stores the schema for each type of career item

data class CareerItem(
    // data members - need: title, category, progress status, completion indicator
    val title: String,
    val category: String, //["Skill Development", "Certification", "Academic Project", "Internship Application", "Career Goal"]
    val progressStatus: String,
    val completionIndicator: String
)
