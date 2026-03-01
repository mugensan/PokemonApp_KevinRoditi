package com.kevinroditi.pokemonapp_kevinroditi.data.local.dao

/**
 * Common model for Pokémon data joined with favorite status from Room
 */
data class PokemonWithFavorite(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val page: Int,
    val isFavorite: Boolean
)
