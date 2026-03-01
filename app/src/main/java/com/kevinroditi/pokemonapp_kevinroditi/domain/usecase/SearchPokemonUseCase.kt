package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    private val pokemonTypes = setOf(
        "fire", "water", "electric", "grass", "psychic", "dragon", 
        "dark", "fairy", "ghost", "steel", "ice", "rock", 
        "ground", "poison", "flying", "bug", "normal", "fighting"
    )

    operator fun invoke(query: String, preferences: SearchPreferences): Flow<PagingData<Pokemon>> {
        return when {
            query.isBlank() -> repository.getPokemonPaging(preferences)
            query.all { it.isDigit() } -> repository.searchById(query.toInt(), preferences)
            query.lowercase() in pokemonTypes -> repository.searchByType(query.lowercase(), preferences)
            else -> repository.searchByName(query.lowercase(), preferences)
        }
    }
}
