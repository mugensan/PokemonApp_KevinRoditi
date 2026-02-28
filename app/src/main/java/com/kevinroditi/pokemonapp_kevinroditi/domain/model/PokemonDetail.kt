package com.kevinroditi.pokemonapp_kevinroditi.domain.model

/**
 * Domain model rep. detailed Pokemon info
 *
 * Responsability:
 * - Provide clean UI-ready data
 * - Be independant of API response structure
 * - Abstract away complex DTO nesting
 *
 * - Created via mapper
 * - Consumed by ViewModel and UI
 */

data class PokemonDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>
)

/**
 * SEPERATING FOR CLARITY AND UI MAPPING
 */
data class Stat(
    val name: String,
    val value: Int
)
