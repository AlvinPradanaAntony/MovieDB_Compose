package com.dicoding.moviesdb_compose.data.model

data class Movies(
    val id: Int,
    val name: String,
    val description: String,
    val photo: String,
    val releaseDate: String,
    val photoCover: String
)
