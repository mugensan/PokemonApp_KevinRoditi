package com.kevinroditi.pokemonapp_kevinroditi.domain.model

data class SearchPreferences(
    val typeFilter: String? = null,
    val onlyFavorites: Boolean = false,
    val sortOrder: SearchSortOrder = SearchSortOrder.DEFAULT
)

enum class SearchSortOrder {
    DEFAULT,
    ID_ASC,
    ID_DESC,
    NAME_ASC,
    NAME_DESC
}
