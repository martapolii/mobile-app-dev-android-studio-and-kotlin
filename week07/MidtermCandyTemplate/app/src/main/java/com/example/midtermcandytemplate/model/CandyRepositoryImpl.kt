package com.example.midtermcandytemplate.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * In-memory repository implementation.
 *
 *  It behaves like a tiny fake
 * database so the app can add, edit, and display items without Room or networking.
 */
class CandyRepositoryImpl : CandyRepository {

    // Shared MutableStateFlow keeps the list reactive across multiple activities.
    private val candyList = MutableStateFlow(starterCandies)

    override fun getCandies(): StateFlow<List<Candy>> = candyList.asStateFlow()

    override fun getCandyById(id: Int): Candy? {
        return candyList.value.firstOrNull { candy ->
            candy.id == id
        }
    }

    override fun addCandy(candy: Candy) {
        val nextId = (candyList.value.maxOfOrNull { item -> item.id } ?: 0) + 1
        candyList.value = candyList.value + candy.copy(id = nextId)
    }

    override fun updateCandy(candy: Candy) {
        candyList.value = candyList.value.map { currentCandy ->
            if (currentCandy.id == candy.id) {
                candy
            } else {
                currentCandy
            }
        }
    }
}
