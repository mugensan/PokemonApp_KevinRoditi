package com.kevinroditi.pokemonapp_kevinroditi.security.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

sealed class BiometricResult {
    object Success : BiometricResult()
    object Failed : BiometricResult()
    object Error : BiometricResult()
    object NotAvailable : BiometricResult()
}

interface BiometricAuthenticator {
    suspend fun authenticate(activity: FragmentActivity): BiometricResult
}

class BiometricAuthenticatorImpl @Inject constructor() : BiometricAuthenticator {

    override suspend fun authenticate(activity: FragmentActivity): BiometricResult = suspendCancellableCoroutine { continuation ->
        val biometricManager = BiometricManager.from(activity)
        val authenticators = BIOMETRIC_STRONG or DEVICE_CREDENTIAL

        if (biometricManager.canAuthenticate(authenticators) != BiometricManager.BIOMETRIC_SUCCESS) {
            continuation.resume(BiometricResult.NotAvailable)
            return@suspendCancellableCoroutine
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(authenticators)
            .setConfirmationRequired(false)
            .build()

        val biometricPrompt = BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    continuation.resume(BiometricResult.Error)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    continuation.resume(BiometricResult.Success)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    continuation.resume(BiometricResult.Failed)
                }
            }
        )

        biometricPrompt.authenticate(promptInfo)
    }
}
