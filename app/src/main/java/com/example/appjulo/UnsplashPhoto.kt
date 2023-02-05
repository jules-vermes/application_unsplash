package com.example.appjulo

data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val description: String?,
    val alt_description: String?,
    val urls: Urls,
    val links: Links,
    val likes: Int,
    val user: User
)

data class User(
    val username: String,
    val name: String,
    val bio: String
)
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class Links(
    val self: String,
    val html: String,
    val download: String,
    val download_location: String
)
