package com.kevinroditi.pokemonapp_kevinroditi.di

import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.PokemonRepository
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.GetPokemonDetailUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.GetPokemonPagingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides domain use case
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonPagingUseCase(
        repository: PokemonRepository
    ): GetPokemonPagingUseCase {
        return GetPokemonPagingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(
        repository: PokemonRepository
    ): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCase(repository)
    }
}
