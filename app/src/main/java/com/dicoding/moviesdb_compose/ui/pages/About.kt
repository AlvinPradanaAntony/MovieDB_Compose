package com.dicoding.moviesdb_compose.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.moviesdb_compose.R
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme
import com.dicoding.moviesdb_compose.ui.theme.whiteSubtitle

@Composable
fun About(
    modifier: Modifier = Modifier,
) {
    AboutContent()
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Box(modifier = Modifier) {
            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 2.dp,
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(75.dp))
                    Text(
                        text = "Alvin Pradana Antony",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = "Email : ",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = whiteSubtitle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "alvinpradanaantony@gmail.com",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Study Group : ",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = whiteSubtitle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "MD-22",
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 26.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Image(
                painter = painterResource(R.drawable.bakcdropth),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .offset(y = (-60).dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter)
                    .shadow(12.dp, CircleShape, clip = true)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    MoviesDB_ComposeTheme {
        About()
    }
}