package com.kevinroditi.pokemonapp_kevinroditi.di

/**
 * Provides domain use case
 *
 * Why a module if constructor works?
 * - Explicit dependency graph def.
 * - Easier to scale when use cases grow
 * - Centralized place to see domain logic wiring
 *
 * Installed in SingletonComponent to ensure single instance
 * accross entire app lifecycle
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