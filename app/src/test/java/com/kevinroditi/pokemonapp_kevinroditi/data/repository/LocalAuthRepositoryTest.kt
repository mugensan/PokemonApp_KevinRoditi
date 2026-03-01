package com.kevinroditi.pokemonapp_kevinroditi.security.repository

import androidx.fragment.app.FragmentActivity
import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricAuthenticator
import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricResult
import com.kevinroditi.pokemonapp_kevinroditi.security.session.SessionManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LocalAuthRepositoryTest {

    @Mock
    private lateinit var authenticator: BiometricAuthenticator

    @Mock
    private lateinit var sessionManager: SessionManager

    private lateinit var repository: LocalAuthRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = LocalAuthRepository(authenticator, sessionManager)
    }

    @Test
    fun `given biometric success, when authenticate, then save session and return success`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        `when`(authenticator.authenticate(activity)).thenReturn(BiometricResult.Success)

        // When
        val result = repository.authenticate(activity)

        // Then
        assertTrue(result.isSuccess)
        verify(sessionManager).saveSession(true)
    }

    @Test
    fun `given biometric failure, when authenticate, then return failure`() = runTest {
        // Given
        val activity = mock<FragmentActivity>()
        `when`(authenticator.authenticate(activity)).thenReturn(BiometricResult.Failed)

        // When
        val result = repository.authenticate(activity)

        // Then
        assertTrue(result.isFailure)
        verify(sessionManager, org.mockito.Mockito.never()).saveSession(anyBoolean())
    }

    @Test
    fun `given session is active, when isUserLoggedIn, then return true`() = runTest {
        // Given
        `when`(sessionManager.isLoggedIn()).thenReturn(true)

        // When
        val result = repository.isUserLoggedIn()

        // Then
        assertTrue(result)
    }

    @Test
    fun `when logout, then clear session`() = runTest {
        // When
        repository.logout()

        // Then
        verify(sessionManager).clearSession()
    }

    private fun anyBoolean(): Boolean = org.mockito.ArgumentMatchers.anyBoolean()
}
