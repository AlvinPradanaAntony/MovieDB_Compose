package com.dicoding.moviesdb_compose.ui.component.navigation

sealed class NavigationRouteScreen(val route: String) {
    object Home : NavigationRouteScreen("home")
    object Favourite : NavigationRouteScreen("favourite")
    object About : NavigationRouteScreen("about")
    object Detail : NavigationRouteScreen("home/{moviesId}") {
        fun createRoute(moviesId: Long) = "home/$moviesId"
    }
}
