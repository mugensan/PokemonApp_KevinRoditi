package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

/*
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.SearchPokemonUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @Mock
    private lateinit var searchPokemonUseCase: SearchPokemonUseCase

    @Mock
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        
        `when`(searchPokemonUseCase(any(), any())).thenReturn(flowOf())
        
        viewModel = HomeViewModel(
            searchPokemonUseCase,
            toggleFavoriteUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when search query changes, then state is updated`() = runTest {
        // When
        viewModel.onSearchQueryChange("pikachu")

        // Then
        assertEquals("pikachu", viewModel.searchQuery.value)
    }

    @Test
    fun `when preferences updated, then state is updated`() = runTest {
        // Given
        val newPrefs = SearchPreferences(onlyFavorites = true)

        // When
        viewModel.updatePreferences(newPrefs)

        // Then
        assertEquals(newPrefs, viewModel.preferences.value)
    }

    @Test
    fun `when favorite toggled, then use case is called`() = runTest {
        // Given
        val pokemon = Pokemon(1, "bulbasaur", "", false)

        // When
        viewModel.toggleFavorite(pokemon)

        // Then
        testDispatcher.scheduler.advanceUntilIdle()
        verify(toggleFavoriteUseCase).invoke(pokemon)
    }
}
*/
