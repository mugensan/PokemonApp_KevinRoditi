package com.kevinroditi.pokemonapp_kevinroditi.data.mapper

import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto

/**
 * Mapper responsable for converting DTOs into Domain models
 *
 * - Decouples API response from business logic
 * - Protects UI from backend changes
 * - Centralizes transformation logic
 *
 * Architecture:
 * - Used by repository implementation and PagingSource
 *
 * Performance:
 * - Avoid unecessary object allocation
 */


object PokemonMapper{
    private const val IMAGE_BASE_URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites" +
                "/master/sprites/pokemon/other/official-artwork"
}

fun mapToPokemonDetail(dto: PokemonDetailDto){
    return PokemonDetail(
        id = dto.id,
        name = dto.name.capitalizeFirstLetter(),
        imageUrl = dto.sprites.other
            ?.official_artwork
            ?.front_default
            ?:buildImageUrl(dto.id),
        height = dto.height,
        weight = dto.weight,
        types = dto.types.map {
            it.type.name.capitalizeFirstLetter()
        },
        abilities = dto.abilities.map {
            it.ability.name.capitalizeFirstLetter()
        },
        stats = dto.stats.map {
            Stat(
                name = it.stat.name.capitalizeFirstLetter(),
                value = it.base_stat
            )
        }
    )
}

/**
 * Extracts Pokemon ID from API URL
 */

private fun extractIdFromUrl(url: String): Int {
    val splitUrl = url.split("/")
        .subStringAfterLast("/")
        .toInOrNull ?: 0
}

/**
 * BUILDING OFFICIAL ARTWORK IMAGE URL
 */

private fun buildImageUrl(id:Int): String{
    return "$IMAGE_BASE_URL/$id.png"
}