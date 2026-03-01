package com.kevinroditi.pokemonapp_kevinroditi.data.repository

/*
import com.kevinroditi.pokemonapp_kevinroditi.core.util.Resource
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.FavoritePokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.PokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.db.AppDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.api.PokeApiService
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.PokemonDetailDto
import com.kevinroditi.pokemonapp_kevinroditi.data.remote.dto.SpriteResponse
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import java.io.IOException

class PokemonRepositoryImplTest {

    @Mock
    private lateinit var api: PokeApiService

    @Mock
    private lateinit var database: AppDatabase

    @Mock
    private lateinit var pokemonDao: PokemonDao

    @Mock
    private lateinit var favoritePokemonDao: FavoritePokemonDao

    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        `when`(database.pokemonDao()).thenReturn(pokemonDao)
        `when`(database.favoritePokemonDao()).thenReturn(favoritePokemonDao)
        repository = PokemonRepositoryImpl(api, database)
    }

    @Test
    fun `given api returns detail, when getPokemonDetail, then return Success resource with mapped data`() = runTest {
        // Given
        val pokemonName = "pikachu"
        val detailDto = PokemonDetailDto(
            id = 25,
            name = "pikachu",
            height = 4,
            weight = 60,
            sprites = SpriteResponse(null),
            types = emptyList(),
            stats = emptyList(),
            abilities = emptyList()
        )
        `when`(api.getPokemonDetail(pokemonName)).thenReturn(detailDto)

        // When
        val result = repository.getPokemonDetail(pokemonName)

        // Then
        assertTrue(result is Resource.Success)
        assertEquals("pikachu", (result as Resource.Success).value.name)
    }

    @Test
    fun `given api throws IOException, when getPokemonDetail, then return Error resource with network message`() = runTest {
        // Given
        val pokemonName = "pikachu"
        `when`(api.getPokemonDetail(pokemonName)).thenAnswer { throw IOException() }

        // When
        val result = repository.getPokemonDetail(pokemonName)

        // Then
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).message.contains("Network error"))
    }

    @Test
    fun `when addFavorite is called, then database insert is triggered`() = runTest {
        // Given
        val pokemon = Pokemon(1, "bulbasaur", "url", false)

        // When
        repository.addFavorite(pokemon)

        // Then
        verify(favoritePokemonDao).insertFavorite(any())
    }

    @Test
    fun `when toggleFavorite called on existing favorite, then delete is triggered`() = runTest {
        // Given
        val pokemon = Pokemon(1, "bulbasaur", "url", true)
        `when`(favoritePokemonDao.isFavoriteSync(pokemon.name)).thenReturn(true)

        // When
        repository.toggleFavorite(pokemon)

        // Then
        verify(favoritePokemonDao).deleteFavoriteById(pokemon.id)
    }
}
*/
