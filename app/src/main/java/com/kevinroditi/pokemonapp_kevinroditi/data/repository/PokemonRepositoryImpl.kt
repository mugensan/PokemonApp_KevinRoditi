package com.kevinroditi.pokemonapp_kevinroditi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.data.mapper.PokemonMapper
import com.kevinroditi.pokemonapp_kevinroditi.data.paging.PokemonPagingSource
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Concrete implementation of PokemonRepository
 */
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService
) : PokemonRepository {

    override fun getPokemonPaging(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonPagingSource(api)
            }
        ).flow
    }

    override suspend fun getPokemonDetail(name: String): Resource<PokemonDetail> {
        return try {
            val response = api.getPokemonDetail(name)
            val mapped = PokemonMapper.mapToPokemonDetail(response)
            Resource.Success(mapped)
        } catch (e: IOException) {
            Resource.Error("Network error. Please check your connection", e)
        } catch (e: HttpException) {
            Resource.Error("Server error: ${e.code()}", e)
        } catch (e: Exception) {
            Resource.Error("Unexpected error", e)
        }
    }
}
