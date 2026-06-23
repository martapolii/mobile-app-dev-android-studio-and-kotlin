package com.vinay.retrofitjsonapp

/**
 * This data class matches the JSON structure returned by the `/posts` endpoint.
 *
 * Gson reads each JSON key and maps it to a property with the same name, so the
 * field names here intentionally mirror the API response.
 */
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
