package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

/**
 * VM for HomeScreen
 *
 * Responsabilities:
 * - Expose paginated Pokemon List to UI
 * - Survive config. changes
 * - Keep UI logic UI separated from business logic
 *
 * Architecture:
 * - Depends on UseCase not directly on Repository
 * - Injected via Hilt
 * - Lifecycle aware via viewModelScope
 *
 * Why cachedIn(viewModelScope)?
 * - Prevents reloading data on config. changes
 * - Keeps paging state consistant during rotation
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonPagingUseCase: GetPokemonPagingUseCase
) : ViewModel() {

    // Flow of paginated Pokemon, so Compose can collect using collectAsLazyPagingItems()
    val pokemonPagingData: Flow<PagingData<Pokemon>> =
        getPokemonPagingUseCase().cachedIn(viewModelScope)
}