package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _preferences = MutableStateFlow(SearchPreferences())
    val preferences: StateFlow<SearchPreferences> = _preferences.asStateFlow()

    val pokemonPagingData: Flow<PagingData<Pokemon>> = combine(
        _searchQuery,
        _preferences
    ) { query, prefs ->
        Pair(query, prefs)
    }
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { (query, prefs) ->
            searchPokemonUseCase(query, prefs)
        }
        .cachedIn(viewModelScope)

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun updatePreferences(newPrefs: SearchPreferences) {
        _preferences.value = newPrefs
    }
}
