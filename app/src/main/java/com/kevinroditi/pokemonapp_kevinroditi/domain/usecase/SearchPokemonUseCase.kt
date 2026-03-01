package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import android.content.Context
import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.R
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository,
    @ApplicationContext private val context: Context
) {
    private val pokemonTypesMap = mapOf(
        "fire" to R.string.type_fire,
        "water" to R.string.type_water,
        "electric" to R.string.type_electric,
        "grass" to R.string.type_grass,
        "psychic" to R.string.type_psychic,
        "dragon" to R.string.type_dragon,
        "dark" to R.string.type_dark,
        "fairy" to R.string.type_fairy,
        "ghost" to R.string.type_ghost,
        "steel" to R.string.type_steel,
        "ice" to R.string.type_ice,
        "rock" to R.string.type_rock,
        "ground" to R.string.type_ground,
        "poison" to R.string.type_poison,
        "flying" to R.string.type_flying,
        "bug" to R.string.type_bug,
        "normal" to R.string.type_normal,
        "fighting" to R.string.type_fighting
    )

    operator fun invoke(query: String, preferences: SearchPreferences): Flow<PagingData<Pokemon>> {
        val normalizedQuery = query.lowercase().trim()
        
        // Check if the query is a translated type name
        val typeKey = pokemonTypesMap.entries.find { (_, resId) ->
            context.getString(resId).lowercase() == normalizedQuery
        }?.key ?: normalizedQuery

        return when {
            normalizedQuery.isBlank() -> repository.getPokemonPaging(preferences)
            normalizedQuery.all { it.isDigit() } -> repository.searchById(normalizedQuery.toInt(), preferences)
            typeKey in pokemonTypesMap.keys -> repository.searchByType(typeKey, preferences)
            else -> repository.searchByName(normalizedQuery, preferences)
        }
    }
}
