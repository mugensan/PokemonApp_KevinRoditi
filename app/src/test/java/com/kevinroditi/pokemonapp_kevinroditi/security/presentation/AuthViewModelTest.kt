package com.kevinroditi.pokemonapp_kevinroditi.security.presentation

import androidx.fragment.app.FragmentActivity
import app.cash.turbine.test
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.AuthenticateUserUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.CheckSessionUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.LogoutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    @Mock
    private lateinit var authenticateUserUseCase: AuthenticateUserUseCase

    @Mock
    private lateinit var checkSessionUseCase: CheckSessionUseCase

    @Mock
    private lateinit var logoutUseCase: LogoutUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given user is logged in, when init, then state is Authenticated`() = runTest {
        // Given
        `when`(checkSessionUseCase()).thenReturn(true)

        // When
        val viewModel = AuthViewModel(authenticateUserUseCase, checkSessionUseCase, logoutUseCase)

        // Then
        viewModel.authState.test {
            assertEquals(AuthState.Authenticated, awaitItem())
        }
    }

    @Test
    fun `given auth succeeds, when authenticate, then state becomes Authenticated`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        `when`(checkSessionUseCase()).thenReturn(false)
        `when`(authenticateUserUseCase(activity)).thenReturn(Result.success(Unit))
        val viewModel = AuthViewModel(authenticateUserUseCase, checkSessionUseCase, logoutUseCase)

        // When
        viewModel.authenticate(activity)

        // Then
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Authenticated, awaitItem())
        }
    }

    @Test
    fun `given auth fails, when authenticate, then state becomes Failed`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        `when`(checkSessionUseCase()).thenReturn(false)
        `when`(authenticateUserUseCase(activity)).thenReturn(Result.failure(Exception("Failed")))
        val viewModel = AuthViewModel(authenticateUserUseCase, checkSessionUseCase, logoutUseCase)

        // When
        viewModel.authenticate(activity)

        // Then
        viewModel.authState.test {
            assertEquals(AuthState.Idle, awaitItem())
            assertEquals(AuthState.Loading, awaitItem())
            assertEquals(AuthState.Failed, awaitItem())
        }
    }

    @Test
    fun `when logout, then use case is called and state is Idle`() = runTest {
        // Given
        `when`(checkSessionUseCase()).thenReturn(true)
        val viewModel = AuthViewModel(authenticateUserUseCase, checkSessionUseCase, logoutUseCase)

        // When
        viewModel.logout()

        // Then
        viewModel.authState.test {
            assertEquals(AuthState.Authenticated, awaitItem())
            assertEquals(AuthState.Idle, awaitItem())
        }
        verify(logoutUseCase).invoke()
    }
}
