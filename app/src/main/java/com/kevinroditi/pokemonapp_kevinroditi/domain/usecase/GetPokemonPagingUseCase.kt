package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase responsible for retreiving paginated Pokemon list
 */
class GetPokemonPagingUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return repository.getPokemonPaging()
    }
}
