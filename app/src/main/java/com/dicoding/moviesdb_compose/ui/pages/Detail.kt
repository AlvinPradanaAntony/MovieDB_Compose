package com.dicoding.moviesdb_compose.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.moviesdb_compose.R
import com.dicoding.moviesdb_compose.UiState
import com.dicoding.moviesdb_compose.ViewModelFactory
import com.dicoding.moviesdb_compose.data.di.Injection
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.ui.theme.colorAccent
import com.dicoding.moviesdb_compose.viewmodels.DetailViewModel

@Composable
fun Detail(
    moviesId: Long,
    moviesViewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    moviesViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                moviesViewModel.getDetailMovies(moviesId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    id = data.id,
                    photo = data.photo,
                    backCover = data.photoCover,
                    title = data.name,
                    description = data.description,
                    releaseDate = data.releaseDate,
                    isFavorite = data.isFavorite,
                    navigateBack = navigateBack,
                    modifier = modifier,
                    onFavoriteButtonClicked = { id, state ->
                        moviesViewModel.checkStatusFavourite(id, state)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    id: Long,
    photo: String,
    backCover: String,
    title: String,
    description: String,
    releaseDate: String,
    isFavorite: Boolean,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onFavoriteButtonClicked: (id: Long, state: Boolean) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp),
        ) {
            Box(modifier = Modifier){
                AsyncImage(
                    colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.5f), BlendMode.Multiply),
                    model = backCover,
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "Back Cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(255.dp)
                )
                AsyncImage(
                    model = photo,
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "Back Cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(y = 18.dp)
                        .align(Alignment.BottomCenter)
                        .width(125.dp)
                        .height(170.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        ),
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Release Date : ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Overview",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .size(40.dp)
                .testTag("back_button")
                .background(MaterialTheme.colors.surface)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
            )
        }
        FloatingActionButton(
            onClick = {  onFavoriteButtonClicked(id, isFavorite) },
            backgroundColor = colorAccent,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = if (!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = "Favorite Button",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    MoviesDB_ComposeTheme {
        Detail(
            moviesId = 1,
            navigateBack = {}
        )
    }
}