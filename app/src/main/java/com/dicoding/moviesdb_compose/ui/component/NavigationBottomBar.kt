package com.dicoding.moviesdb_compose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dicoding.moviesdb_compose.R
import com.dicoding.moviesdb_compose.ui.component.navigation.NavigationItem
import com.dicoding.moviesdb_compose.ui.component.navigation.NavigationRouteScreen
import com.dicoding.moviesdb_compose.ui.theme.MoviesDB_ComposeTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        elevation = 4.dp,
        modifier = modifier
            .shadow(48.dp)
            .clip(RoundedCornerShape(28.dp))
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = NavigationRouteScreen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favourite),
                icon = Icons.Default.Favorite,
                screen = NavigationRouteScreen.Favourite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = NavigationRouteScreen.About
            ),
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavigation {
            navigationItems.map { item ->
                val curentSelected = currentRoute == item.screen.route
                BottomNavigationItem(
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )

                            if(curentSelected) {
                                Text(
                                    text = item.title,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    },
                    selected = curentSelected,
                    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarItemPreview() {
    MoviesDB_ComposeTheme {
        BottomBar(
            navController = NavHostController(LocalContext.current),
            modifier = Modifier
        )
    }
}