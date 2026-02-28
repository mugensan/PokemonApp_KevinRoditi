package com.kevinroditi.pokemonapp_kevinroditi.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.FavoritePokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity

/**
 * Main Room DB
 */
@Database(
    entities = [FavoritePokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritePokemonDao(): FavoritePokemonDao
}
