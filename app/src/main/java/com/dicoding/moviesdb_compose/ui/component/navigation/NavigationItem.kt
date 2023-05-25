package com.dicoding.moviesdb_compose.ui.component.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: NavigationRouteScreen,
    val contentDescription: String
)
