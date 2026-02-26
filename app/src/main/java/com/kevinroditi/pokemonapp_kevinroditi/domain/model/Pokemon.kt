package com.kevinroditi.pokemonapp_kevinroditi.domain.model

/**
 * Pokemon displayed in the HomeScreen
 *
 * Model intentionally minimal because it is used in a paginated list
 *
 * Responsability:
 * - Provide clean UI-ready data
 * - Be independant of API response structure
 *
 * - Used by Presentation Layer
 *
 * Performance considerations:
 * - Lightweight for smooth scroll
 * - Avoiding carrying heavy nested objects from API
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String
)