package com.kevinroditi.pokemonapp_kevinroditi.domain.repository

import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon

/**
 * Repository contract for pokemon related orperations.
 *
 * Responsability:
 * - define business required data ops
 * - Remain independant of impl details
 *
 * Architecture:
 * - Data layer implements this interface
 * - UserCases depend on this interface
 * - Presentation never talks directly to Data layer
 *
 * Why:
 * - Enables testability
 * - Enables swapping data sources (remote, local, cached)
 * - Clean architecture boundaries repected
 */



interface PokemonRepository {

    // @return Flow<PagingData<Pokemon>>
    fun getPokemonPaging():
            Flow<PagingData<Pokemon>>
}

//@Params name Pokemon name
suspend fun getPokemonDetail(name: String):
        Resource<PokemonDetail>