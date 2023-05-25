package com.dicoding.moviesdb_compose.data.db

import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.data.model.MoviesDBData

class RepositoryMovies {
    fun getMovies(): List<Movies> {
        return MoviesDBData.movies
    }

    fun searchMovies(query: String): List<Movies> {
        return MoviesDBData.movies.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}