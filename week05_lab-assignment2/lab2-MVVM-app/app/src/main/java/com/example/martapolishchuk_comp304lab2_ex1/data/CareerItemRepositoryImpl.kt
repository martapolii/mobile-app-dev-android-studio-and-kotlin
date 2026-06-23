package com.example.martapolishchuk_comp304lab2_ex1.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Marta Polishchuk - 301432299

// Career Item Repository Implementation
class CareerItemRepositoryImpl : CareerItemRepository { // inherits from the repository

    // keeping the data in one repository object makes all 3 screens share the same state
    private val careerItemList = MutableStateFlow(
        listOf(
            CareerItem(
                id = 1,
                title = "android portfolio app",
                description = "finish the course project and polish the ui",
                category = "Academic Project",
                startDate = "2026-06-01",
                targetCompletionDate = "2026-06-25",
                status = "In Progress",
                progressPercentage = 70
            ),
            CareerItem(
                id = 2,
                title = "aws cloud practitioner",
                description = "study weekly and complete the certification exam",
                category = "Certification",
                startDate = "2026-05-15",
                targetCompletionDate = "2026-07-10",
                status = "Not Started",
                progressPercentage = 0
            ),
            CareerItem(
                id = 3,
                title = "summer internship applications",
                description = "apply to mobile and software roles each week",
                category = "Internship Application",
                startDate = "2026-06-05",
                targetCompletionDate = "2026-08-01",
                status = "In Progress",
                progressPercentage = 40
            )
        )
    )

    // definition of the getCareerItems & get by ID methods:
    override fun getCareerItems(): StateFlow<List<CareerItem>> = careerItemList.asStateFlow()

    override fun getCareerItemById(id: Int): CareerItem? {
        return careerItemList.value.firstOrNull { it.id == id }
    }

    // definition of the addCareerItem method:
    override fun addCareerItem(careerItem: CareerItem) {
        val nextId = (careerItemList.value.maxOfOrNull { it.id } ?: 0) + 1
        careerItemList.value = careerItemList.value + careerItem.copy(id = nextId)
    }

    // definition of the updateCareerItem method:
    override fun updateCareerItem(careerItem: CareerItem) {
        careerItemList.value = careerItemList.value.map { currentCareerItem ->
            if (currentCareerItem.id == careerItem.id) {
                careerItem
            } else {
                currentCareerItem
            }
        }
    }
}
