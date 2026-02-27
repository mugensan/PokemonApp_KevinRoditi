package com.kevinroditi.pokemonapp_kevinroditi.presentation.navigation

import com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.DetailScreen

/**
 * Central nav.
 *
 * Responsibilities:
 * - Defining all routes in one place
 * - Handle argument passing
 * - Keep nav. scalable
 *
 * Why centralized?
 * - Avoids magic strings across UI
 * - Makes adding new screens easier
 * - Improve readability
 */

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    )

    // HomeScreen
    composable(Screen.Home.route) {
        HomeScreen(
            onPokemonClick = { name ->
                navController.navigate(Screen.Detail.createRoute(name))
            }
        )
    }

    // DetailScreen
    // Receives Pokemon name as argument
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