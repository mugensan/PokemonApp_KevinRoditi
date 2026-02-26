package com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto

/**
 * Represents paginated response from:
 *  GET /pokemon?limit=X&offset=Y
 *
 *  - Contains pagination metadata
 *  - Contains list of named pokemon resources
 *
 *  Never exposed directly to UI
 */
data class PokemonlistResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResourceDto>
)