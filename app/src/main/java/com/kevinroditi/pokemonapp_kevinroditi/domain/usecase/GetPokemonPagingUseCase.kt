package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

/**
 * UseCase responsible for retreiving paginated Pokemon list
 *
 * Why UseCase Layer:
 * - Encapsulate business logic
 * - Decouples ViewModel from Repository
 * - Makes testing easier
 *
 * Architecture:
 * - ViewModel depends on this
 * - This depends on Repository
 * - Repository depends on Data Layer
 */

class GetPokemonPagingUseCase @Inject constructor(
    private val repository: PokemonRepository
){

    // Invoked by VM to obtain paginated Pokemon
    operator fun invoke(): Flow<PagingData<Pokemon>>{
        return repository.getPokemonPaging()
    }
}