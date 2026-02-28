package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * UseCase responsible for retreiving detailed Pokemon info
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Resource<PokemonDetail> {
        return repository.getPokemonDetail(name)
    }
}
