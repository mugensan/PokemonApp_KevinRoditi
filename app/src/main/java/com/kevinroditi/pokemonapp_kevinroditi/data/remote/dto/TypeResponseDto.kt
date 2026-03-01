package com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto

data class TypeResponseDto(
    val pokemon: List<TypePokemonEntryDto>
)

data class TypePokemonEntryDto(
    val pokemon: NamedApiResourceDto
)
