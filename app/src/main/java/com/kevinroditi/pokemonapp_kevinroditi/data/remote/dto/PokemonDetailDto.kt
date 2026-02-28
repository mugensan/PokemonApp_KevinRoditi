package com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto

import com.squareup.moshi.Json

/**
 * Represents Pokemon Detail information from:
 * GET /pokemon/{name}
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

data class TypeResponse(
    val type: NamedApiResourceDto
)

data class StatResponse(
    val base_stat: Int,
    val stat: NamedApiResourceDto
)

data class AbilityResponse(
    val ability: NamedApiResourceDto
)

data class SpriteResponse(
    val other: OtherSpriteResponse?
)

data class OtherSpriteResponse(
    @field:Json(name = "official-artwork")
    val official_artwork: OfficialArtworkResponse?
)

data class OfficialArtworkResponse(
    val front_default: String?
)
