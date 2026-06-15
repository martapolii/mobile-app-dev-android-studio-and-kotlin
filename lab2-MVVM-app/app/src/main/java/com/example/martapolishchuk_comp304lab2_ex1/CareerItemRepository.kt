package com.example.martapolishchuk_comp304lab2_ex1

import androidx.lifecycle.LiveData

// Career Item Repository - interface for repository to implement dependency injection

interface CareerItemRepository {
    // signature's of methods to be used to manipulate the data model are listed here:

    // 1. Get a Career Item
    fun getCareerItems(): LiveData<List<CareerItem>> // get details of the list

    // 2. Add a new Career Item
    fun addCareerItem(careerItem: CareerItem) // pass the object

    // 3. Edit an existing Career Item
}