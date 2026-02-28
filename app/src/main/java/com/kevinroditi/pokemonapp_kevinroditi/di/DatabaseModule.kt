package com.kevinroditi.pokemonapp_kevinroditi.di

import android.content.Context
import androidx.room.Room
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.FavoritePokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides Room DB dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(
        database: AppDatabase
    ): FavoritePokemonDao = database.favoritePokemonDao()
}
