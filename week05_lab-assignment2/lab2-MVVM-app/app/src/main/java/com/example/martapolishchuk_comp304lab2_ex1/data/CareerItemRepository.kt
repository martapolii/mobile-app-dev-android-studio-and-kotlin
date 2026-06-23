package com.example.martapolishchuk_comp304lab2_ex1.data

import kotlinx.coroutines.flow.StateFlow

// Marta Polishchuk - 301432299

// Career Item Repository - interface for repository to implement dependency injection

interface CareerItemRepository {
    // signature's of methods to be used to manipulate the data model are listed here:

    // 1. Get all Career Items
    fun getCareerItems(): StateFlow<List<CareerItem>> // get details of the list

    // 2. Get one Career Item
    fun getCareerItemById(id: Int): CareerItem?

    // 3. Add a new Career Item
    fun addCareerItem(careerItem: CareerItem) // pass the object

    // 4. Edit an existing Career Item
    fun updateCareerItem(careerItem: CareerItem)
}
