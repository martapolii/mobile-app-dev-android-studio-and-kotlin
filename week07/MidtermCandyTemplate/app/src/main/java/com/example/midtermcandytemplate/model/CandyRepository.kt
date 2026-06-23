package com.example.midtermcandytemplate.model

import kotlinx.coroutines.flow.StateFlow

/**
 * Repository contract used by every screen.
 *
 * This mirrors the lab 2 pattern where the UI talks to a repository instead of
 * modifying the list directly.
 */
interface CandyRepository {
    fun getCandies(): StateFlow<List<Candy>>
    fun getCandyById(id: Int): Candy?
    fun addCandy(candy: Candy)
    fun updateCandy(candy: Candy)
}
