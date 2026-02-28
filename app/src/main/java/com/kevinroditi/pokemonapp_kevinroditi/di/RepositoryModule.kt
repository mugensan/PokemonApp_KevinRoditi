package com.kevinroditi.pokemonapp_kevinroditi.di

import com.kevinroditi.pokemonapp_kevinroditi.data.repository.PokemonRepositoryImpl
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds repository impl. to its interface
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        impl: PokemonRepositoryImpl
    ): PokemonRepository
}
