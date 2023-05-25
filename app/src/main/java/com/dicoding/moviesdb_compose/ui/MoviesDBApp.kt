package com.dicoding.moviesdb_compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.moviesdb_compose.R
import com.dicoding.moviesdb_compose.data.model.Movies
import com.dicoding.moviesdb_compose.ui.component.BottomBar
import com.dicoding.moviesdb_compose.ui.component.navigation.NavigationRouteScreen
import com.dicoding.moviesdb_compose.ui.pages.*
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme

@Composable
fun MoviesDBApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            if (checkStatusRoute(currentRoute)) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.height(40.dp)
                    )
                }
            }

        },
        bottomBar = {
            if (checkStatusRoute(currentRoute)) {
                BottomBar(
                    navController,
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRouteScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationRouteScreen.Home.route) {
                Home(
                    navigateToDetail = { movieId ->
                        navController.navigate(NavigationRouteScreen.Detail.createRoute(movieId))
                    }
                )
            }
            composable(NavigationRouteScreen.Favourite.route) {
                Favorite(
                    navigateToDetail = { movieId ->
                        navController.navigate(NavigationRouteScreen.Detail.createRoute(movieId))
                    }
                )
            }
            composable(NavigationRouteScreen.About.route) {
                About()
            }
            composable(
                route = NavigationRouteScreen.Detail.route,
                arguments = listOf(navArgument("moviesId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("moviesId") ?: -1
                Detail(
                    moviesId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MoviesDBAppPreview() {
    MoviesDB_ComposeTheme {
        MoviesDBApp()
    }
}

fun checkStatusRoute(currentRoute: String?): Boolean {
    return currentRoute != NavigationRouteScreen.Detail.route
}

