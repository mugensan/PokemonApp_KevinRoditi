package com.kevinroditi.pokemonapp_kevinroditi.data.paging

import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService

/**
 * PagingSource responsible for laoding Pokemon in chuncks from the API
 *
 * Why PagingSource:
 * - Efficient memory usage
 * - Handles large datasets without loading everything at once
 * - Smooth infinite scrolling
 *
 * Architecture:
 * - Lives in data Layer
 * - Uses PokeApiServices
 * - Maps DTO -> Domain using PokemonMapper
 *
 * Key decision:
 * - Offset-based pagination(PokeAp uses limit + offset)
 * - Errors handles and propagates correctly
 */

class PokemonPagingSource(
    private val api: PokeApiService
): PagingSource<Int, Pokemon>() {

    companion object{
        private const val INITIAL_OFFSET = 0
        private const val PAGE_SIZE = 20
    }

    /**Core pagin  logic
     *
     * Called automatically by Paging3 when more dagta is needed
     */

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon>{
        val offset = params.key ?: INITIAL_OFFSET

        return try{
            val response = api.getPokemonList(
                limit = PAGE_SIZE,
                offset = offset
            )

            val pokemons = response.results.map{
                PokemonMapper.mapToPokemon(it)

            }

            val nextOffset = if(response.next != null){
                offset + PAGE_SIZE
            }else{
                null
            }

            LoadResult.Page(
                data = pokemons,
                prevKey = if(offset == INITIAL_OFSET) null
            else offset - PAGE_SIZE,
                nextKey = nextOffset
            )
        }catch(e:IOExeption) {
            //Network error (no internet, timeout, etc...)
            LoadResult.Error(e)
        }catch(e:HttpExeption){
            //non-2xx HTTP response
            LoadResult.Error(e)
        }
    }

    /**
     * - Determines which key to use when refreshing
     * - Ensures stable scroll position after refresh
     */
    override fun getRefreshKey(state: PagingState<Int,Pokemon>): Int?{
        return state.anchorPosition?.let{
            position ->
            state.closestPageToPosition(position)?.prevKey?.plus(PAGE_SIZE)
                ?:
                state.closestPageToPosition(position)?.nextKey?.minus(PAGE_SIZE)
        }
    }
}