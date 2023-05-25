package com.dicoding.moviesdb_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.moviesdb_compose.data.repository.RepositoryMovies
import com.dicoding.moviesdb_compose.ui.pages.Favorite
import com.dicoding.moviesdb_compose.viewmodels.DetailViewModel
import com.dicoding.moviesdb_compose.viewmodels.FavouriteViewModel
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

class ViewModelFactory(private val repository: RepositoryMovies) : ViewModelProvider.NewInstanceFactory()   {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> {
                FavouriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
