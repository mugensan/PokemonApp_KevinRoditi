package com.kevinroditi.pokemonapp_kevinroditi.data.mapper

import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.NamedApiResourceDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Stat

/**
 * Mapper responsable for converting DTOs into Domain models
 */

object PokemonMapper {
    private const val IMAGE_BASE_URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites" +
                "/master/sprites/pokemon/other/official-artwork"

    fun mapToPokemon(dto: NamedApiResourceDto): Pokemon {
        val id = extractIdFromUrl(dto.url)
        return Pokemon(
            id = id,
            name = dto.name.capitalizeFirstLetter(),
            imageUrl = buildImageUrl(id)
        )
    }

    fun mapToPokemonDetail(dto: PokemonDetailDto): PokemonDetail {
        return PokemonDetail(
            id = dto.id,
            name = dto.name.capitalizeFirstLetter(),
            imageUrl = dto.sprites.other
                ?.official_artwork
                ?.front_default
                ?: buildImageUrl(dto.id),
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
                    name = it.stat.name,
                    value = it.base_stat
                )
            }
        )
    }

    private fun buildImageUrl(id: Int): String {
        return "$IMAGE_BASE_URL/$id.png"
    }

    /**
     * Extracts Pokemon ID from API URL
     */
    fun extractIdFromUrl(url: String): Int {
        return url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull() ?: 0
    }
}
