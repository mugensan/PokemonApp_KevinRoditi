package com.kevinroditi.pokemonapp_kevinroditi.presentation.navigation

/**
 * Sealed class for def. nav. routes
 *
 * Why sealed class instead of raw string?
 * - Type safety
 * - Prevents typos
 * - Centralized route management
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{name}") {
        const val ARG_NAME = "name"
        fun createRoute(name: String): String {
            return "detail/$name"
        }
    }
}