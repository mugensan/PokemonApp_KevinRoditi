package com.kevinroditi.pokemonapp_kevinroditi.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.data.local.db.AppDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.mapper.PokemonMapper
import com.kevinroditi.pokemonapp_kevinroditi.data.paging.PokemonRemoteMediator
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Concrete implementation of PokemonRepository with Offline Caching
 */
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService,
    private val database: AppDatabase
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPaging(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = PokemonRemoteMediator(api, database),
            pagingSourceFactory = {
                database.pokemonDao().pagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                PokemonMapper.mapToPokemon(entity)
            }
        }
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

    override fun getFavorites(): Flow<List<Pokemon>> {
        return database.favoritePokemonDao().getFavorites().map { entities ->
            entities.map { entity ->
                Pokemon(id = entity.id, name = entity.name, imageUrl = entity.imageUrl)
            }
        }
    }

    override fun isFavorite(name: String): Flow<Boolean> {
        return database.favoritePokemonDao().isFavorite(name)
    }

    override suspend fun addFavorite(pokemon: Pokemon) {
        database.favoritePokemonDao().addFavorite(
            FavoritePokemonEntity(id = pokemon.id, name = pokemon.name, imageUrl = pokemon.imageUrl)
        )
    }

    override suspend fun removeFavorite(name: String) {
        database.favoritePokemonDao().removeFavorite(name)
    }
}
