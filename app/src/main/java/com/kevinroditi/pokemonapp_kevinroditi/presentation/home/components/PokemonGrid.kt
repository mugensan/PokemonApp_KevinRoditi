package com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon

@Composable
fun PokemonGrid(
    pokemonPagingItems: LazyPagingItems<Pokemon>,
    onPokemonClick: (String) -> Unit,
    onFavoriteClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                count = pokemonPagingItems.itemCount,
                key = pokemonPagingItems.itemKey { it.name },
                contentType = pokemonPagingItems.itemContentType { "pokemon" }
            ) { index ->
                pokemonPagingItems[index]?.let { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onClick = { onPokemonClick(pokemon.name) },
                        onFavoriteClick = { onFavoriteClick(pokemon) }
                    )
                }
            }

            // Append loading indicator
            if (pokemonPagingItems.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        // Initial refresh loading indicator
        if (pokemonPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Error state handling
        if (pokemonPagingItems.loadState.refresh is LoadState.Error) {
            Text(
                text = "An error occurred. Please try again.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        // Empty state
        if (pokemonPagingItems.loadState.refresh is LoadState.NotLoading && pokemonPagingItems.itemCount == 0) {
            Text(
                text = "No Pokémon found.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
