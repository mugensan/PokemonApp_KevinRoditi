package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * UseCase to add or remove a Pokémon from favorites
 */
class ToggleFavoriteUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) {
        repository.toggleFavorite(pokemon)
    }
}
