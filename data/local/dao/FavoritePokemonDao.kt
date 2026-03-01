package com.kevinroditi.pokemonapp_kevinroditi.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.FavoritePokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePokemonDao {

    @Query("SELECT * FROM favorite_pokemon")
    fun getFavorites(): Flow<List<FavoritePokemonEntity>>

    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getFavoritesList(): List<FavoritePokemonEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE name = :name)")
    fun isFavorite(name: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE name = :name)")
    suspend fun isFavoriteSync(name: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: FavoritePokemonEntity)

    @Query("DELETE FROM favorite_pokemon WHERE name = :name")
    suspend fun deleteFavorite(name: String)

    @Query("SELECT * FROM favorite_pokemon ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, FavoritePokemonEntity>
}
