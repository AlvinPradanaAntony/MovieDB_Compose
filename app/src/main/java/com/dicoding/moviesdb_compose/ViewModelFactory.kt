package com.dicoding.moviesdb_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.moviesdb_compose.data.db.RepositoryMovies
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

class ViewModelFactory(private val repository: RepositoryMovies) : ViewModelProvider.NewInstanceFactory()   {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
