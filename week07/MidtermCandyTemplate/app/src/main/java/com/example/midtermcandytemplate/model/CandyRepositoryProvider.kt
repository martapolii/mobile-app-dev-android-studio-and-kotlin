package com.example.midtermcandytemplate.model

/**
 * Shared repository holder for the template.
 *
 * This uses the same singleton `object` idea shown by week 6's `RetrofitClient`:
 * one shared object that multiple screens can access while the app is running.
 */
object CandyRepositoryProvider {
    val repository: CandyRepository by lazy {
        CandyRepositoryImpl()
    }
}
