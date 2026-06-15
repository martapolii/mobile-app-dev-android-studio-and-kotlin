package com.example.martapolishchuk_comp304lab2_ex1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Career Item Repository Implementation

class CareerItemRepositoryImpl : CareerItemRepository { // inherits from the repository

    // live data to hold the list of career items:
    private val careerItemList = MutableLiveData<List<CareerItem>>(emptyList())

    // definition of the getCareerItems method:
    override fun getCareerItems(): LiveData<List<CareerItem>> = careerItemList

    // definition of the addCareerItem method:
    override fun addCareerItem(careerItem: CareerItem) {
        careerItemList.value = careerItemList.value?.plus(element = careerItem)
    }
}