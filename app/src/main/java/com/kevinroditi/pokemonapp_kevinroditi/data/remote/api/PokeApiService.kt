package com.kevinroditi.pokemonapp_kevinroditi.data.remote.api

import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonlistResponseDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service def. for PokeAPI
 *
 * Base_url: https://pokeapi.co/api/v2
 *
 * - Defining http endpoints
 * - Providing suspend functions for coroutine support
 *
 * - Used only by RepoImpl
 *
 * Performance:
 * - Non-blocking suspend functions
 * - Pagination handled using limit + offset
 */

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        //Default poke page is 20
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): PokemonlistResponseDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto

}
