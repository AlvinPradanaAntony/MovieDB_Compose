package com.dicoding.moviesdb_compose.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.moviesdb_compose.UiState
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.data.repository.RepositoryMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: RepositoryMovies) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movies>>> =
        MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getFavoriteMovie() = viewModelScope.launch {
        repository.getFavouriteMovie()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }
}