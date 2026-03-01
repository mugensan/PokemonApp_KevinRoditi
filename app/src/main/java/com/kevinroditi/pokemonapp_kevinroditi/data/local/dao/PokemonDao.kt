package com.kevinroditi.pokemonapp_kevinroditi.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kevinroditi.pokemonapp_kevinroditi.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("""
        SELECT pokemon.*, (favorite_pokemon.id IS NOT NULL) AS isFavorite 
        FROM pokemon 
        LEFT JOIN favorite_pokemon ON pokemon.id = favorite_pokemon.id 
        ORDER BY pokemon.id ASC
    """)
    fun pagingSource(): PagingSource<Int, PokemonWithFavorite>

    @Query("SELECT * FROM pokemon WHERE name LIKE :query")
    suspend fun searchByName(query: String): List<PokemonEntity>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}
