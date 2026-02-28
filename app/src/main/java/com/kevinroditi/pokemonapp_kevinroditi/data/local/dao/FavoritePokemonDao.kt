package com.kevinroditi.pokemonapp_kevinroditi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO handles database ops.
 */
@Dao
interface FavoritePokemonDao {

    @Query("SELECT * FROM favorite_pokemon")
    fun getFavorites(): Flow<List<FavoritePokemonEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE name = :name)")
    fun isFavorite(name: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(pokemon: FavoritePokemonEntity)

    @Query("DELETE FROM favorite_pokemon WHERE name = :name")
    suspend fun removeFavorite(name: String)
}
