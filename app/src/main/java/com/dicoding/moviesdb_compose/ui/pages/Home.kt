package com.dicoding.moviesdb_compose.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.moviesdb_compose.ViewModelFactory
import com.dicoding.moviesdb_compose.data.db.RepositoryMovies
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.ui.component.ListDataMovies
import com.dicoding.moviesdb_compose.ui.component.SearchBar
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

@Composable
fun Home(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column() {
        HomeContent()
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    moviesViewModel: MoviesViewModel = viewModel(factory = ViewModelFactory(RepositoryMovies())),
) {
    val movies by moviesViewModel.movies.collectAsState()
    val query by moviesViewModel.query
/*    val groupedHeroes by moviesViewModel.groupedHeroes.collectAsState()
    val query by moviesViewModel.query*/
        SearchBar(
            query = query,
            onQueryChange = moviesViewModel::search,
            modifier = modifier
                .fillMaxWidth()
        )

    if (movies.isNotEmpty()) {
        ListDataMovies(movies)
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Data Kosong",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    MoviesDB_ComposeTheme {
        Home(
            navigateToDetail = {}
        )
    }
}