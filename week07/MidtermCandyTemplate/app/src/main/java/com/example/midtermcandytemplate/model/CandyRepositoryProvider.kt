package com.example.midtermcandytemplate.model

/**
 * Tiny service locator for the template.
 *
 * Multiple activities need to share one repository instance so edits done in the
 * detail screen are visible when the user returns to the home screen.
 */
object CandyRepositoryProvider {
    val repository: CandyRepository by lazy {
        CandyRepositoryImpl()
    }
}
