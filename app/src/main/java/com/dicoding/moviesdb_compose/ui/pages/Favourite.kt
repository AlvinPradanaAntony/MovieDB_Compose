package com.dicoding.moviesdb_compose.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.moviesdb_compose.UiState
import com.dicoding.moviesdb_compose.ViewModelFactory
import com.dicoding.moviesdb_compose.data.di.Injection
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.ui.component.ListDataMovies
import com.dicoding.moviesdb_compose.ui.component.SearchBar
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.viewmodels.FavouriteViewModel
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

@Composable
fun Favorite(
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    moviesViewModel: FavouriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    moviesViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                moviesViewModel.getFavoriteMovie()
            }
            is UiState.Success -> {
                FavoriteContent(
                    listMovie = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listMovie: List<Movies>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        if (listMovie.isNotEmpty()) {
            ListDataMovies(
                listMovie = listMovie,
                navigateToDetail = navigateToDetail,
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "Data Favourite Kosong",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteContentPreview() {
    MoviesDB_ComposeTheme {
        FavoriteContent(
            listMovie = listOf(),
            navigateToDetail = {})
    }
}