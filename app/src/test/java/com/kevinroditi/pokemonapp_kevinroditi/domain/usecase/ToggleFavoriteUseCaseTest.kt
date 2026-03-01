package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ToggleFavoriteUseCaseTest {

    @Mock
    private lateinit var repository: PokemonRepository

    private lateinit var useCase: ToggleFavoriteUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = ToggleFavoriteUseCase(repository)
    }

    @Test
    fun `when invoke, then repository toggleFavorite is called`() = runTest {
        // Given
        val pokemon = Pokemon(1, "bulbasaur", "url", false)

        // When
        useCase(pokemon)

        // Then
        verify(repository).toggleFavorite(pokemon)
    }
}
