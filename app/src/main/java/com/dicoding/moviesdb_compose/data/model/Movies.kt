package com.dicoding.moviesdb_compose.data.model

data class Movies(
    val id: Long,
    val name: String,
    val description: String,
    val photo: String,
    val releaseDate: String,
    val photoCover: String,
    val isFavorite: Boolean = false,
)
