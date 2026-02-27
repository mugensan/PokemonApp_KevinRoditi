package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail

/**
 * UseCase responsible for retreiving detailed Pokemon info
 *
 * Responsabilities:
 * - Encapsulate detail fetching logic
 * - Provide single entry point for VM
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    // Fetch Pokemon detail by name
    suspend operator fun invoke(name: String):
            Resource<PokemonDetail> {
        return repository.getPokemonDetail(name)
    }
}