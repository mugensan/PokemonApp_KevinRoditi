package com.kevinroditi.pokemonapp_kevinroditi.domain.repository

import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for pokemon related operations.
 */
interface PokemonRepository {
    fun getPokemonPaging(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetail(name: String): Resource<PokemonDetail>

    fun getFavorites(): Flow<List<Pokemon>>

    fun isFavorite(name: String): Flow<Boolean>

    suspend fun addFavorite(pokemon: Pokemon)

    suspend fun removeFavorite(name: String)
}
