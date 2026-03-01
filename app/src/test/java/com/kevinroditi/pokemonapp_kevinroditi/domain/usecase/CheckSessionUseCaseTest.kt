package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CheckSessionUseCaseTest {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var useCase: CheckSessionUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = CheckSessionUseCase(repository)
    }

    @Test
    fun `given user is logged in, when invoke, then return true`() = runTest {
        // Given
        `when`(repository.isUserLoggedIn()).thenReturn(true)

        // When
        val result = useCase()

        // Then
        assertTrue(result)
    }

    @Test
    fun `given user is not logged in, when invoke, then return false`() = runTest {
        // Given
        `when`(repository.isUserLoggedIn()).thenReturn(false)

        // When
        val result = useCase()

        // Then
        assertFalse(result)
    }
}
