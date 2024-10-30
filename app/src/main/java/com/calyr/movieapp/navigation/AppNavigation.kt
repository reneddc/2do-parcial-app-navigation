package com.calyr.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.calyr.movieapp.screen.MovieDetailScreen
import com.calyr.movieapp.screen.MoviesScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MoviesScreen.route
    ) {
        composable(Screens.MoviesScreen.route) {
            MoviesScreen(
                onClick = {
                   movie -> navController.navigate("${Screens.MovieDetailScreen.route}/${Json.encodeToString(movie)}")
                }
            )
        }
        composable(
            route = "${Screens.MovieDetailScreen.route}/{movie}",
            arguments = listOf(
                navArgument("movie") {
                    type = NavType.StringType
                }
            )
        ) {
            MovieDetailScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                movie = Json.decodeFromString(it.arguments?.getString("movie")?:"")
            )
        }
    }
}