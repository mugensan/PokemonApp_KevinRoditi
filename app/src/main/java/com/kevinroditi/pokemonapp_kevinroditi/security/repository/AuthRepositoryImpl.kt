package com.kevinroditi.pokemonapp_kevinroditi.security.repository

import androidx.fragment.app.FragmentActivity
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricAuthenticator
import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricResult
import com.kevinroditi.pokemonapp_kevinroditi.security.session.SessionManager
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticator: BiometricAuthenticator,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun authenticate(activity: FragmentActivity): Result<Unit> {
        return when (val result = authenticator.authenticate(activity)) {
            is BiometricResult.Success -> {
                sessionManager.saveSession(true)
                Result.success(Unit)
            }
            is BiometricResult.Failed -> Result.failure(Exception("Authentication failed"))
            is BiometricResult.Error -> Result.failure(Exception("Authentication error"))
            is BiometricResult.NotAvailable -> Result.failure(Exception("Biometrics not available"))
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return sessionManager.isLoggedIn()
    }

    override suspend fun logout() {
        sessionManager.clearSession()
    }

    override suspend fun getCurrentUserId(): String? {
        // Placeholder for future backend support
        return if (isUserLoggedIn()) "local_user" else null
    }
}
