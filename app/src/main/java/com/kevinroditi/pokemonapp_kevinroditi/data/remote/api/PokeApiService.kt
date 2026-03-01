package com.kevinroditi.pokemonapp_kevinroditi.data.remote.api

import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonlistResponseDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.TypeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): PokemonlistResponseDto

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto

    @GET("type/{type}")
    suspend fun getPokemonByType(
        @Path("type") type: String
    ): TypeResponseDto
}
