package com.dicoding.moviesdb_compose.data.repository

import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.data.model.MoviesDBData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RepositoryMovies {
    private val listMovies = mutableListOf<Movies>()

    init {
        if (listMovies.isEmpty()) {
            listMovies.addAll(MoviesDBData.movies)
        }
    }

    fun getAllListMovies(): Flow<List<Movies>> {
        return flowOf(listMovies)
    }

    fun getItemMovieById(moviesId: Long): Flow<Movies> {
        return flowOf(listMovies.first {
            it.id == moviesId
        })
    }

    fun getFavouriteMovie(): Flow<List<Movies>> {
        return flowOf(listMovies.filter {
            it.isFavorite
        })
    }

    fun searchMovies(query: String): Flow<List<Movies>> {
        return flowOf(listMovies.filter {
            it.name.contains(query, ignoreCase = true)
        })
    }

    fun updateDataMovies(moviesId: Long, state: Boolean): Flow<Boolean> {
        val index = listMovies.indexOfFirst { it.id == moviesId }
        val result = if (index >= 0) {
            val movie = listMovies[index]
            listMovies[index] = movie.copy(isFavorite = state)
            true
        } else {
            false
        }
        return flowOf(result)
    }


    companion object {
        @Volatile
        private var instance: RepositoryMovies? = null

        fun getInstance(): RepositoryMovies =
            instance ?: synchronized(this) {
                RepositoryMovies().apply {
                    instance = this
                }
            }
    }

}