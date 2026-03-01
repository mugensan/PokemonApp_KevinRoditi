package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

/*
import androidx.paging.PagingData
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchPokemonUseCaseTest {

    @Mock
    private lateinit var repository: PokemonRepository

    private lateinit var useCase: SearchPokemonUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = SearchPokemonUseCase(repository)
    }

    @Test
    fun `given blank query, when invoke, then call getPokemonPaging`() {
        // Given
        val query = ""
        val preferences = SearchPreferences()
        `when`(repository.getPokemonPaging(preferences)).thenReturn(flowOf(PagingData.empty()))

        // When
        useCase(query, preferences)

        // Then
        verify(repository).getPokemonPaging(preferences)
    }

    @Test
    fun `given numeric query, when invoke, then call searchById`() {
        // Given
        val query = "25"
        val preferences = SearchPreferences()
        `when`(repository.searchById(25, preferences)).thenReturn(flowOf(PagingData.empty()))

        // When
        useCase(query, preferences)

        // Then
        verify(repository).searchById(25, preferences)
    }

    @Test
    fun `given valid type query, when invoke, then call searchByType`() {
        // Given
        val query = "fire"
        val preferences = SearchPreferences()
        `when`(repository.searchByType("fire", preferences)).thenReturn(flowOf(PagingData.empty()))

        // When
        useCase(query, preferences)

        // Then
        verify(repository).searchByType("fire", preferences)
    }

    @Test
    fun `given invalid type query, when invoke, then call searchByName`() {
        // Given
        val query = "notatype"
        val preferences = SearchPreferences()
        `when`(repository.searchByName("notatype", preferences)).thenReturn(flowOf(PagingData.empty()))

        // When
        useCase(query, preferences)

        // Then
        verify(repository).searchByName("notatype", preferences)
    }

    @Test
    fun `given mixed case query, when invoke, then call searchByName with lowercase`() {
        // Given
        val query = "PikaChu"
        val preferences = SearchPreferences()
        `when`(repository.searchByName("pikachu", preferences)).thenReturn(flowOf(PagingData.empty()))

        // When
        useCase(query, preferences)

        // Then
        verify(repository).searchByName("pikachu", preferences)
    }
}
*/
