package com.kevinroditi.pokemonapp_kevinroditi.di

/**
 * Binds repository impl. to its interface
 *
 * Why:
 * - Domain layer depends on PokemonRepository
 * - Data layer provides PokemonRepositoryImpl
 * - Hilt needs to know which one to inject
 *
 * Architecture:
 * - Uses @Binds instead of @Provides for better perf.
 * - Installed in SingletonComponent to ensure single instance
 *   accross entire app lifecycle
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds PokemonRepositoryImpl to PokemonRepository
     */
    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        impl: PokemonRepositoryImpl
    ): PokemonRepository
}