package com.kevinroditi.pokemonapp_kevinroditi.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.PokemonWithFavorite
import com.kevinroditi.pokemonapp_kevinroditi.data.local.db.AppDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.mapper.PokemonMapper
import com.kevinroditi.pokemonapp_kevinroditi.data.paging.PokemonRemoteMediator
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchSortOrder
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Enterprise Repository Implementation
 * Handles data merging between Remote API and Local Room Database
 */
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService,
    private val database: AppDatabase
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPaging(preferences: SearchPreferences): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = if (!preferences.onlyFavorites) PokemonRemoteMediator(api, database) else null,
            pagingSourceFactory = {
                if (preferences.onlyFavorites) {
                    database.favoritePokemonDao().pagingSource()
                } else {
                    database.pokemonDao().pagingSource()
                }
            }
        ).flow.map { pagingData ->
            pagingData.map { item ->
                Pokemon(
                    id = item.id,
                    name = item.name,
                    imageUrl = item.imageUrl,
                    isFavorite = item.isFavorite
                )
            }
        }
    }

    override fun searchByName(query: String, preferences: SearchPreferences): Flow<PagingData<Pokemon>> = flow {
        val results = if (preferences.onlyFavorites) {
            database.favoritePokemonDao().getFavoritesList()
                .filter { it.name.contains(query, ignoreCase = true) }
                .map { Pokemon(it.id, it.name, it.imageUrl, true) }
        } else {
            database.pokemonDao().searchByName("%$query%")
                .map { entity ->
                    val isFav = database.favoritePokemonDao().isFavoriteSync(entity.name)
                    Pokemon(entity.id, entity.name, entity.imageUrl, isFav)
                }
        }
        emit(PagingData.from(results.sort(preferences.sortOrder)))
    }

    override fun searchById(id: Int, preferences: SearchPreferences): Flow<PagingData<Pokemon>> = flow {
        try {
            val response = api.getPokemonDetail(id.toString())
            val isFav = database.favoritePokemonDao().isFavoriteSync(response.name)
            val pokemon = Pokemon(
                id = response.id,
                name = response.name,
                imageUrl = response.sprites.other?.official_artwork?.front_default ?: "",
                isFavorite = isFav
            )
            
            if (preferences.onlyFavorites && !isFav) {
                emit(PagingData.empty())
            } else {
                emit(PagingData.from(listOf(pokemon)))
            }
        } catch (e: Exception) {
            emit(PagingData.empty())
        }
    }

    override fun searchByType(type: String, preferences: SearchPreferences): Flow<PagingData<Pokemon>> = flow {
        try {
            val response = api.getPokemonByType(type)
            val pokemonList = response.pokemon.map { entry ->
                val id = PokemonMapper.extractIdFromUrl(entry.pokemon.url)
                val isFav = database.favoritePokemonDao().isFavoriteSync(entry.pokemon.name)
                Pokemon(
                    id = id,
                    name = entry.pokemon.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
                    isFavorite = isFav
                )
            }
            
            val filteredList = if (preferences.onlyFavorites) {
                pokemonList.filter { it.isFavorite }
            } else {
                pokemonList
            }
            
            emit(PagingData.from(filteredList.sort(preferences.sortOrder)))
        } catch (e: Exception) {
            emit(PagingData.empty())
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
                Pokemon(id = entity.id, name = entity.name, imageUrl = entity.imageUrl, isFavorite = true)
            }
        }
    }

    override fun getFavoritePokemonPaging(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { database.favoritePokemonDao().pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { item ->
                Pokemon(id = item.id, name = item.name, imageUrl = item.imageUrl, isFavorite = true)
            }
        }
    }

    override fun observeFavoriteStatus(pokemonId: Int): Flow<Boolean> {
        return database.favoritePokemonDao().observeFavoriteById(pokemonId).map { it != null }
    }

    override suspend fun toggleFavorite(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            val isFav = database.favoritePokemonDao().isFavoriteSync(pokemon.name)
            if (isFav) {
                database.favoritePokemonDao().deleteFavoriteById(pokemon.id)
            } else {
                database.favoritePokemonDao().insertFavorite(
                    FavoritePokemonEntity(id = pokemon.id, name = pokemon.name, imageUrl = pokemon.imageUrl)
                )
            }
        }
    }

    override fun isFavorite(name: String): Flow<Boolean> {
        return database.favoritePokemonDao().isFavorite(name)
    }

    override suspend fun addFavorite(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            database.favoritePokemonDao().insertFavorite(
                FavoritePokemonEntity(id = pokemon.id, name = pokemon.name, imageUrl = pokemon.imageUrl)
            )
        }
    }

    override suspend fun removeFavorite(name: String) {
        withContext(Dispatchers.IO) {
            database.favoritePokemonDao().deleteFavorite(name)
        }
    }

    private fun List<Pokemon>.sort(order: SearchSortOrder): List<Pokemon> {
        return when(order) {
            SearchSortOrder.DEFAULT -> this
            SearchSortOrder.ID_ASC -> sortedBy { it.id }
            SearchSortOrder.ID_DESC -> sortedByDescending { it.id }
            SearchSortOrder.NAME_ASC -> sortedBy { it.name }
            SearchSortOrder.NAME_DESC -> sortedByDescending { it.name }
        }
    }
}
