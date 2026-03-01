package com.kevinroditi.pokemonapp_kevinroditi.domain.repository

import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for Pokémon related operations.
 */
interface PokemonRepository {
    fun getPokemonPaging(preferences: SearchPreferences = SearchPreferences()): Flow<PagingData<Pokemon>>

    fun searchByName(query: String, preferences: SearchPreferences = SearchPreferences()): Flow<PagingData<Pokemon>>

    fun searchById(id: Int, preferences: SearchPreferences = SearchPreferences()): Flow<PagingData<Pokemon>>

    fun searchByType(type: String, preferences: SearchPreferences = SearchPreferences()): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetail(name: String): Resource<PokemonDetail>

    fun getFavorites(): Flow<List<Pokemon>>

    fun getFavoritePokemonPaging(): Flow<PagingData<Pokemon>>

    fun observeFavoriteStatus(pokemonId: Int): Flow<Boolean>

    suspend fun toggleFavorite(pokemon: Pokemon)

    fun isFavorite(name: String): Flow<Boolean>

    suspend fun addFavorite(pokemon: Pokemon)

    suspend fun removeFavorite(name: String)
}
