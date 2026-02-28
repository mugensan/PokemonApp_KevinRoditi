package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

import com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components.PokemonCard
import java.lang.reflect.Modifier

/**
 * HomeScreen displaying paginated Pokemon grid
 *
 * Architecture:
 * - Collects paging data from VM
 * - Stateless regarding business logic
 * - Nav handled via lambda
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val pokemonPagingItems = viewModel
        .pokemonPagingData
        .collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pokemon App")
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)

                    ) {
                        items(pokemonPagingItems) { pokemon ->
                            pokemon.let {
                                PokemonCard(
                                    pokemon = it,
                                    onClick = {
                                        onPokemonClick(it.name)
                                    }
                                )
                            }
                        }
                    }
                    // Initial loading indicator
                    if (pokemonPagingItems.loadState.refresh is LoadState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

        }
    )
}