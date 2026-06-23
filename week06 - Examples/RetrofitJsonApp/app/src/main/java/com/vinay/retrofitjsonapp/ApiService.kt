package com.vinay.retrofitjsonapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * Retrofit turns this interface into a real implementation at runtime.
 *
 * The `@GET("posts")` annotation says "call the /posts endpoint", and `suspend`
 * allows this function to run inside a coroutine without blocking the main thread.
 */
interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

/**
 * A singleton Retrofit client keeps one shared networking configuration for the app.
 */
object RetrofitClient {
    // `by lazy` waits until the first network call before creating Retrofit.
    val api: ApiService by lazy {
        Retrofit.Builder()
            // The base URL must end with a trailing slash so Retrofit can append `posts`.
            .baseUrl(BASE_URL)
            // GsonConverterFactory converts JSON text into our `Post` data class objects.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
