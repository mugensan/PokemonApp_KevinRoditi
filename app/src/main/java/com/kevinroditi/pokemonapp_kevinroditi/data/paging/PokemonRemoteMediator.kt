package com.kevinroditi.pokemonapp_kevinroditi.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.PokemonWithFavorite
import com.kevinroditi.pokemonapp_kevinroditi.data.local.db.AppDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.PokemonEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.RemoteKeyEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.mapper.PokemonMapper
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApiService,
    private val database: AppDatabase
) : RemoteMediator<Int, PokemonWithFavorite>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonWithFavorite>
    ): MediatorResult {
        val offset = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToPosition(state)
                remoteKeys?.nextKey?.minus(state.config.pageSize) ?: 0
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val response = api.getPokemonList(
                limit = state.config.pageSize,
                offset = offset
            )

            val isEndOfList = response.next == null
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().clearRemoteKeys()
                    database.pokemonDao().clearAll()
                }
                val prevKey = if (offset == 0) null else offset - state.config.pageSize
                val nextKey = if (isEndOfList) null else offset + state.config.pageSize
                
                val keys = response.results.map {
                    val id = PokemonMapper.extractIdFromUrl(it.url)
                    RemoteKeyEntity(id = id, prevKey = prevKey, nextKey = nextKey)
                }
                database.remoteKeyDao().insertAll(keys)
                
                val entities = response.results.map {
                    val id = PokemonMapper.extractIdFromUrl(it.url)
                    PokemonEntity(
                        id = id,
                        name = it.name,
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
                        page = offset / state.config.pageSize
                    )
                }
                database.pokemonDao().insertAll(entities)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonWithFavorite>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemon ->
                database.remoteKeyDao().remoteKeysId(pokemon.id)
            }
    }

    private suspend fun getRemoteKeyClosestToPosition(state: PagingState<Int, PokemonWithFavorite>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeyDao().remoteKeysId(id)
            }
        }
    }
}
