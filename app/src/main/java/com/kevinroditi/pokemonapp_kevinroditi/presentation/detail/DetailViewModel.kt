package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail

import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource

/**
 * VM for DetailScreen
 *
 * Responsibilities:
 * - Fetch Pokémon detail
 * - Expose UI state via StateFlow
 * - Handle loading and error states
 *
 * Architecture:
 * - Uses UseCase not repo directly
 * - Exposes immutable state to UI
 * - Lifecycle aware via viewModelScope
 *
 * Why stateFlow instead of LiveDate?
 * - Coroutine native
 * - Better Compose integration
 * - More predictable state management
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _uistate = MutableStateFlow<DetailUiState>(DetailUiState())

    /**
     * Public inmutable state exposed to UI
     *
     * This prevents UI from modifying state
     */
    val uiState: StateFlow<DetailUiState> = _uistate.asStateFlow()

    /**
     * Fetch Pokemon detail
     *
     * Called from UI when entering screen
     */
    fun loadPokemon(name: String) {
        viewModelScope.launch {

            // Setting loading state first
            _uistate.value = DetailUiState(
                isLoading = true
            )

            when (val result = getPokemonDetailUseCase(name)) {
                is Resource.Success -> {
                    _uistate.value = DetailUiState(
                        isLoading = false,
                        pokemon = result.data
                    )
                }

                is Resource.Error -> {
                    _uistate.value = DetailUiState(
                        isLoading = false,
                        error = result.message
                    )
                }

                is Resource.Loading -> {
                    _uistate.value = DetailUiState(
                        isLoading = true
                    )
                }
            }
        }

    }
}