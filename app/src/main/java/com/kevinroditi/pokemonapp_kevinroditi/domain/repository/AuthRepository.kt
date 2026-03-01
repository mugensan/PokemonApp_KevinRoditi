package com.kevinroditi.pokemonapp_kevinroditi.domain.repository

import androidx.fragment.app.FragmentActivity

/**
 * Domain-level abstraction for authentication.
 * This allows swapping between local biometrics and Firebase without changing business logic.
 */
interface AuthRepository {
    /**
     * Triggers the authentication process.
     * @param activity The activity context required for biometric prompts.
     * @return Result wrapping Success or Failure.
     */
    suspend fun authenticate(activity: FragmentActivity): Result<Unit>

    /**
     * Checks if a user session is active.
     */
    suspend fun isUserLoggedIn(): Boolean

    /**
     * Clears the current session.
     */
    suspend fun logout()

    /**
     * Returns the unique identifier for the current user.
     * Useful for future backend integration.
     */
    suspend fun getCurrentUserId(): String?
}
