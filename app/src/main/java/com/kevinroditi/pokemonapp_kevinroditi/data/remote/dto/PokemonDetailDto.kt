package com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto

/**
 * Represents Pokemon Detail information from:
 * GET /pokemon/{name}
 *
 * Performance:
 * - Avoid mapping unused fields
 * - Reduces object size
 *
 * Architecture:
 * - DTO must be converted into Domain model view Mapper
 */

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeResponse>,
    val stats: List<StatResponse>,
    val abilities: List<AbilityResponse>,
    val sprites: SpriteResponse
)

// Nested type information
data class TypeResponse(
    val type: NamedApiResourceDto
)

// Nested stat information
data class StatResponse(
    val base_stat: Int,
    val stat: NamedApiResourceDto
)

// Nested ability information
data class AbilityResponse(
    val ability: NamedApiResourceDto
)

// Sprite information (only official artwork used)
data class SpriteResponse(
    val other: OtherSpriteResponse?
)

data class OtherSpriteResponse(
    val official_artwork: OfficialArtworkResponse?
)

data class OfficialArtworkResponse(
    val front_default: String?
)