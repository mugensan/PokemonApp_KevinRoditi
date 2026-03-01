package com.kevinroditi.pokemonapp_kevinroditi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for persistent favorites
 */
@Entity(tableName = "favorite_pokemon")
data class FavoritePokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String
)
