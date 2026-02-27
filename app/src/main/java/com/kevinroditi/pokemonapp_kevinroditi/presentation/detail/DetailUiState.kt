package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail

/**
 * Encouraging single source of truth
 */
data class DetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetail? = null,
    val error: String? = null
)