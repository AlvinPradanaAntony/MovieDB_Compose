package com.dicoding.moviesdb_compose.ui.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.moviesdb_compose.R
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.data.model.MoviesDBData
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.ui.theme.colorAccent
import com.dicoding.moviesdb_compose.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListDataMovies(
    listMovie: List<Movies>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
){
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listMovie, key = { it.id }) { item ->
                MoviesListItem(
                    photo = item.photo,
                    title = item.name,
                    description = item.description,
                    releaseDate = item.releaseDate,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(tween(durationMillis = 100))
                        .clickable { navigateToDetail(item.id) }
                )
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp, end = 15.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListMoviesDataPreview() {
    MoviesDB_ComposeTheme {
        ListDataMovies(
            listMovie = MoviesDBData.movies,
            navigateToDetail = {}
        )
    }
}

@Composable
fun MoviesListItem(
    photo: String,
    title: String,
    description: String,
    releaseDate: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = photo,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(85.dp)
                    .height(120.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorAccent),
                        contentAlignment = Alignment.Center,

                    ){
                        Text(
                            text = releaseDate,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colors.surface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = description,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesListItemPreview() {
    MoviesDB_ComposeTheme {
        MoviesListItem(
            photo = R.drawable.placeholder.toString(),
            title = "Title",
            description = "Description",
            releaseDate = "Release Date"
        )
    }
}