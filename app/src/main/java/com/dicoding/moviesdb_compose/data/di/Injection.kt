package com.dicoding.moviesdb_compose.data.di

import com.dicoding.moviesdb_compose.data.repository.RepositoryMovies

object Injection {
    fun provideRepository(): RepositoryMovies {
        return RepositoryMovies.getInstance()
    }
}