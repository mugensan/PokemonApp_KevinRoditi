package com.kevinroditi.pokemonapp_kevinroditi.security.repository

import androidx.fragment.app.FragmentActivity
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * FUTURE: Firebase Authentication implementation.
 * Currently disabled in v2.5.
 */
class FirebaseAuthRepository @Inject constructor() : AuthRepository {

    override suspend fun authenticate(activity: FragmentActivity): Result<Unit> {
        return Result.failure(Exception("Firebase Authentication not yet implemented"))
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return false
    }

    override suspend fun logout() {
    }

    override suspend fun getCurrentUserId(): String? {
        return null
    }
}
