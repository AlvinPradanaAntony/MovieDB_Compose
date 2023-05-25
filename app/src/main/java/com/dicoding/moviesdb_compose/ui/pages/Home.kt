package com.dicoding.moviesdb_compose.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.moviesdb_compose.UiState
import com.dicoding.moviesdb_compose.ViewModelFactory
import com.dicoding.moviesdb_compose.data.di.Injection
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.data.repository.RepositoryMovies
import com.dicoding.moviesdb_compose.ui.component.ListDataMovies
import com.dicoding.moviesdb_compose.ui.component.SearchBar
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

@Composable
fun Home(
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    val query by moviesViewModel.query
    moviesViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                moviesViewModel.search(query)
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = moviesViewModel::search,
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
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listMovie: List<Movies>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = modifier
                .fillMaxWidth()
        )

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
                    text = "Data Kosong",
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    MoviesDB_ComposeTheme {
        HomeContent(
            query = "",
            onQueryChange = {},
            listMovie = listOf(),
            navigateToDetail = {},
        )
    }
}