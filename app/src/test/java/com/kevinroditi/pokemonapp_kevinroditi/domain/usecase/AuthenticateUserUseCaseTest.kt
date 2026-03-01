package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

/*
import androidx.fragment.app.FragmentActivity
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class AuthenticateUserUseCaseTest {

    @Mock
    private lateinit var repository: AuthRepository

    private lateinit var useCase: AuthenticateUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = AuthenticateUserUseCase(repository)
    }

    @Test
    fun `given repository returns success, when invoke, then return success result`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        `when`(repository.authenticate(activity)).thenReturn(Result.success(Unit))

        // When
        val result = useCase(activity)

        // Then
        assertTrue(result.isSuccess)
    }

    @Test
    fun `given repository returns failure, when invoke, then return failure result`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        val exception = Exception("Auth failed")
        `when`(repository.authenticate(activity)).thenReturn(Result.failure(exception))

        // When
        val result = useCase(activity)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
*/
