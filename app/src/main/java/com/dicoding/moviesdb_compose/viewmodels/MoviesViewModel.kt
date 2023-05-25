package com.dicoding.moviesdb_compose.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.moviesdb_compose.UiState
import com.dicoding.moviesdb_compose.data.repository.RepositoryMovies
import com.dicoding.moviesdb_compose.data.model.Movies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: RepositoryMovies): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movies>>> = MutableStateFlow(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchMovies(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun getAllDataMovies() {
        viewModelScope.launch {
            repository.getAllListMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}