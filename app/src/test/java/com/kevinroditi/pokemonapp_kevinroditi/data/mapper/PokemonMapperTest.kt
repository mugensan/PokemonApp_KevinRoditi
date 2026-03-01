package com.kevinroditi.pokemonapp_kevinroditi.data.mapper

import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.NamedApiResourceDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.SpriteResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonMapperTest {

    @Test
    fun `given named api resource, when mapToPokemon, then return correct pokemon model`() {
        // Given
        val dto = NamedApiResourceDto(name = "pikachu", url = "https://pokeapi.co/api/v2/pokemon/25/")

        // When
        val result = PokemonMapper.mapToPokemon(dto)

        // Then
        assertEquals(25, result.id)
        assertEquals("Pikachu", result.name)
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png", result.imageUrl)
    }

    @Test
    fun `given pokemon detail dto, when mapToPokemonDetail, then return correct detail model`() {
        // Given
        val dto = PokemonDetailDto(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            sprites = SpriteResponse(null),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList()
        )

        // When
        val result = PokemonMapper.mapToPokemonDetail(dto)

        // Then
        assertEquals(25, result.id)
        assertEquals("Pikachu", result.name)
        assertEquals(4, result.height)
        assertEquals(60, result.weight)
    }

    @Test
    fun `given url with id, when extractIdFromUrl, then return correct id`() {
        // Given
        val url = "https://pokeapi.co/api/v2/pokemon/1/"

        // When
        val result = PokemonMapper.extractIdFromUrl(url)

        // Then
        assertEquals(1, result)
    }
}
