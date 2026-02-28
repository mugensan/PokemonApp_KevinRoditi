package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.GetPokemonPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * VM for HomeScreen
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    // Explicitly using the Flow extension to avoid ambiguity with LiveData
    val pokemonPagingData: Flow<PagingData<Pokemon>> =
        getPokemonPagingUseCase().cachedIn(viewModelScope)
}
