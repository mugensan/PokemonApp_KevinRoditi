package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail

import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail

/**
 * UI State for the Detail Screen
 */
data class DetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetail? = null,
    val error: String? = null
)
