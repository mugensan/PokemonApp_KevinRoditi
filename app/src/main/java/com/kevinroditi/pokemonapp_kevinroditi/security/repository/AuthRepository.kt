package com.kevinroditi.pokemonapp_kevinroditi.security.repository

import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricResult

interface AuthRepository {
    suspend fun authenticate(): BiometricResult
    fun isLoggedIn(): Boolean
    fun logout()
}
