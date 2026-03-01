package com.kevinroditi.pokemonapp_kevinroditi.presentation.navigation

/**
 * Sealed class for def. nav. routes
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object Detail : Screen("detail/{name}") {
        const val ARG_NAME = "name"
        fun createRoute(name: String): String {
            return "detail/$name"
        }
    }
}
