package com.kevinroditi.pokemonapp_kevinroditi.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.FavoritePokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.PokemonDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.dao.RemoteKeyDao
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.PokemonEntity
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.RemoteKeyEntity

@Database(
    entities = [
        FavoritePokemonEntity::class,
        PokemonEntity::class,
        RemoteKeyEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritePokemonDao(): FavoritePokemonDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
