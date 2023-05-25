package com.dicoding.moviesdb_compose.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.moviesdb_compose.ViewModelFactory
import com.dicoding.moviesdb_compose.data.db.RepositoryMovies
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel

@Composable
fun Detail(
    movieId: Int,
    moviesViewModel: MoviesViewModel = viewModel(
        factory = ViewModelFactory(RepositoryMovies())
    ),
    modifier: Modifier = Modifier,
) {
    val movies by moviesViewModel.movies.collectAsState()
    val movie = movies.first() {
        it.id == movieId
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Detail",
        )
    }
}