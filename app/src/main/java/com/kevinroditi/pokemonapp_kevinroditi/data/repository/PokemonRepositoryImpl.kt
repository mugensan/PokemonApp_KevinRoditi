package com.kevinroditi.pokemonapp_kevinroditi.data.repository

/**
 * Conctrete implementation of PokemonRepository
 *
 * Responsability:
 * - Connect API to Domain Layer
 * - Convert DTOs to Domain models using Mapper
 * - Handle errors wrapping via Resource
 *
 * Architecture:
 * - Lives in Data Layer
 * - Injected via Hilt
 * - Implements Domain contract
 *
 * Why:
 * - Keeps domain layer pure
 * - Allows easy Unit testing by mocking interface
 */
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApiService
):PokemonRepository {

    /**
     * Provides paginated Pokemon list
     *
     * UsesPaging3's Page to:
     * - Handle page loading auto.
     * - Cache pages in memory
     * - Emit Flow<PagingData<Pokemon>>
     */

    override fun getPokemonPaging():
            Flow<PagingData<Pokemon>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceHolder = false
        ),
            pagingSourceFactory = {
                PokemonPagingSource(api)
            }
        ).flow
    }

    /**
     * Retrieves detailed Pokemon info
     *
     * Wrapped in Resource to standardize UI state handling
     */
    override suspend fun getPokemonDetail(name:String):Resource<PokemonDetail>{
        return try{
            val response = api.getPoemonDetail(name)
            val mapped =
                PokemonMapper.mapToPokemonDetail(response)
                Resource.Success(mapped)
        }catch(e:IOException){
            Resource.Error("Network error. Please check your connection")
        }catch(e:HttpException){
            Resource.Error("Server error: ${e.code()}")
        }catch(e:Exception){
            Resource.Error("Unexpected error")
        }
    }
}