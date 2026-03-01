package com.kevinroditi.pokemonapp_kevinroditi.di

import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricAuthenticator
import com.kevinroditi.pokemonapp_kevinroditi.security.biometric.BiometricAuthenticatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module providing security dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {
    
    @Provides
    @Singleton
    fun provideBiometricAuthenticator(): BiometricAuthenticator {
        return BiometricAuthenticatorImpl()
    }
}
