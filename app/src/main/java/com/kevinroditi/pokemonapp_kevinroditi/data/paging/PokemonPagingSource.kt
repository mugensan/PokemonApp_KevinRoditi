package com.kevinroditi.pokemonapp_kevinroditi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kevinroditi.pokemonapp_kevinroditi.data.mapper.PokemonMapper
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException

/**
 * PagingSource responsible for loading Pokemon in chunks from the API
 */
class PokemonPagingSource(
    private val api: PokeApiService
) : PagingSource<Int, Pokemon>() {

    companion object {
        private const val INITIAL_OFFSET = 0
        private const val PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: INITIAL_OFFSET

        return try {
            val response = api.getPokemonList(
                limit = PAGE_SIZE,
                offset = offset
            )

            val pokemons = response.results.map {
                PokemonMapper.mapToPokemon(it)
            }

            val nextOffset = if (response.next != null) {
                offset + PAGE_SIZE
            } else {
                null
            }

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == INITIAL_OFFSET) null else offset - PAGE_SIZE,
                nextKey = nextOffset
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(PAGE_SIZE)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(PAGE_SIZE)
        }
    }
}
