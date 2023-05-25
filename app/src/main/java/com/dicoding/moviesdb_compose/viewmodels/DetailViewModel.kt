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

class DetailViewModel(private val repository: RepositoryMovies): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Movies>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun checkStatusFavourite(moviesId: Long, state: Boolean) = viewModelScope.launch {
        repository.updateDataMovies(moviesId, !state)
            .collect { isUpdated ->
                if (isUpdated) getDetailMovies(moviesId)
            }
    }

    fun getDetailMovies(id: Long) = viewModelScope.launch {
        repository.getItemMovieById(id)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }.collect{
                _uiState.value = UiState.Success(it)
            }
    }
}