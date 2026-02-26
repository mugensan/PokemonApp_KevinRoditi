package com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto

/**
 * Represents a minimal named resource returned by PokeApi
 *
 * example: {
 *  "name": "bulbasaur",
 *  "url": "https://pokeapi.co/api/v2/pokemon/1/"
 * }
 *
 * Why:
 *  - Used inside paginated list responses
 *  - Allows extracting Pokemon ID from URL
 *
 *  Never exposed directly to UI
 */
data class NamedApiResourceDto(
    val name: String,
    val url: String
)