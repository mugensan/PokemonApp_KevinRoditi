package com.kevinroditi.pokemonapp_kevinroditi.domain.model

/**
 * Pokemon displayed in the UI
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean = false
)
