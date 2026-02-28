package com.kevinroditi.pokemonapp_kevinroditi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.DetailScreen
import com.kevinroditi.pokemonapp_kevinroditi.presentation.home.HomeScreen

/**
 * Central nav.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // HomeScreen
        composable(Screen.Home.route) {
            HomeScreen(
                onPokemonClick = { name ->
                    navController.navigate(Screen.Detail.createRoute(name))
                }
            )
        }

        // DetailScreen
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(Screen.Detail.ARG_NAME) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString(Screen.Detail.ARG_NAME)
                ?: return@composable

            DetailScreen(
                name = name,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
