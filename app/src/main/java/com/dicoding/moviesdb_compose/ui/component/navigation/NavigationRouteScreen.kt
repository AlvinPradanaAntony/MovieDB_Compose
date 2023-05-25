package com.dicoding.moviesdb_compose.ui.component.navigation

sealed class NavigationRouteScreen(val route: String) {
    object Home : NavigationRouteScreen("home")
    object Favourite : NavigationRouteScreen("favourite")
    object About : NavigationRouteScreen("about")
    object Info: NavigationRouteScreen("info")
    object Detail : NavigationRouteScreen("detail/{movieId}") {
        fun createRoute(movieId: Int): String {
            return "detail/$movieId"
        }
    }
}
