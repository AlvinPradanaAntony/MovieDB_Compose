package com.dicoding.moviesdb_compose.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dicoding.moviesdb_compose.data.db.RepositoryMovies
import com.dicoding.moviesdb_compose.data.model.Movies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesViewModel(private val repository: RepositoryMovies): ViewModel() {
    private val _movies = MutableStateFlow(
        repository.getMovies()
    )
    val movies: StateFlow<List<Movies>> get() = _movies

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _movies.value = repository.searchMovies(_query.value)
    }
}