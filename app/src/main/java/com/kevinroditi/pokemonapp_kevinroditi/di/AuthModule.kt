package com.kevinroditi.pokemonapp_kevinroditi.di

import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import com.kevinroditi.pokemonapp_kevinroditi.security.repository.LocalAuthRepository
import com.kevinroditi.pokemonapp_kevinroditi.security.repository.FirebaseAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        localAuthRepository: LocalAuthRepository
    ): AuthRepository

    /**
     * FUTURE: To enable Firebase, uncomment the binding below and comment out the LocalAuthRepository binding.
     * Note: You will also need to add google-services.json and initialize Firebase in the Application class.
     */
    /*
    @Binds
    @Singleton
    abstract fun bindFirebaseAuthRepository(
        firebaseAuthRepository: FirebaseAuthRepository
    ): AuthRepository
    */
}
