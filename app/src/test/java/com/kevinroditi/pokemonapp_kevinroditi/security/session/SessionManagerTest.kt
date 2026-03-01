package com.kevinroditi.pokemonapp_kevinroditi.security.session

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class SessionManagerTest {

    private lateinit var sessionManager: SessionManager
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        sessionManager = SessionManager(context)
    }

    @Test
    fun `given session is saved as true, when isLoggedIn, then return true`() {
        // When
        sessionManager.saveSession(true)

        // Then
        assertTrue(sessionManager.isLoggedIn())
    }

    @Test
    fun `given session is cleared, when isLoggedIn, then return false`() {
        // Given
        sessionManager.saveSession(true)

        // When
        sessionManager.clearSession()

        // Then
        assertFalse(sessionManager.isLoggedIn())
    }

    @Test
    fun `given new session manager instance, when session was saved, then it persists`() {
        // Given
        sessionManager.saveSession(true)

        // When
        val newSessionManager = SessionManager(context)

        // Then
        assertTrue(newSessionManager.isLoggedIn())
    }
}
