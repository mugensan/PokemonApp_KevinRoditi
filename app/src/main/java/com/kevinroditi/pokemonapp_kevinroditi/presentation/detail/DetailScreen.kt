package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kevinroditi.pokemonapp_kevinroditi.R
import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.PokemonDetail
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Stat
import com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.components.StatBar
import com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.components.TypeChip
import com.kevinroditi.pokemonapp_kevinroditi.ui.theme.PokeAppApplicationTheme

@Composable
fun DetailScreen(
    name: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val isFav by viewModel.isFavorite(name).collectAsState()

    LaunchedEffect(name) {
        viewModel.loadPokemon(name)
    }

    DetailContent(
        name = name,
        state = state,
        isFavorite = isFav,
        onBackClick = onBackClick,
        onFavoriteToggle = { pokemon ->
            viewModel.toggleFavorite(pokemon)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    name: String,
    state: DetailUiState,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteToggle: (Pokemon) -> Unit
) {
    val pokemon = state.pokemon
    val contentAlpha by animateFloatAsState(
        targetValue = if (pokemon != null) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "detail_fade_animation"
    )

    // Heart animation
    val heartScale by animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 200f),
        label = "heart_scale_animation"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name.capitalizeFirstLetter()) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                actions = {
                    pokemon?.let {
                        IconButton(onClick = {
                            onFavoriteToggle(
                                Pokemon(id = it.id, name = it.name, imageUrl = it.imageUrl)
                            )
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Toggle favorite",
                                tint = if (isFavorite) Color.Red else Color.Gray,
                                modifier = Modifier.scale(heartScale)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            } else if (pokemon != null) {
                val colorScheme = MaterialTheme.colorScheme
                val typography = MaterialTheme.typography
                
                val backgroundColor = remember(pokemon.types, colorScheme.primary) {
                    val firstType = pokemon.types.firstOrNull()?.lowercase()
                    when (firstType) {
                        "fire" -> Color(0xFFF08030)
                        "water" -> Color(0xFF6890F0)
                        "electric" -> Color(0xFFF8D030)
                        "grass" -> Color(0xFF78C850)
                        "normal" -> Color(0xFFA8A878)
                        else -> colorScheme.primary
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor.copy(alpha = 0.1f))
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp)
                        .alpha(contentAlpha)
                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = pokemon.name,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (type in pokemon.types) {
                            TypeChip(type = type)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(text = stringResource(R.string.about), style = typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = stringResource(R.string.weight), style = typography.labelMedium)
                            Text(text = "${pokemon.weight / 10f} kg", style = typography.bodyLarge)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = stringResource(R.string.height), style = typography.labelMedium)
                            Text(text = "${pokemon.height / 10f} m", style = typography.bodyLarge)
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(text = stringResource(R.string.base_stats), style = typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    for (stat in pokemon.stats) {
                        StatBar(stat = stat, color = backgroundColor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(text = stringResource(R.string.abilities), style = typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = pokemon.abilities.joinToString(", ") { it.capitalizeFirstLetter() },
                        style = typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PokeAppApplicationTheme {
        DetailContent(
            name = "ditto",
            state = DetailUiState(
                pokemon = PokemonDetail(
                    id = 132,
                    name = "ditto",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png",
                    height = 3,
                    weight = 40,
                    types = listOf("normal"),
                    stats = listOf(
                        Stat("hp", 48),
                        Stat("attack", 48),
                        Stat("defense", 48),
                        Stat("special-attack", 48),
                        Stat("special-defense", 48),
                        Stat("speed", 48)
                    ),
                    abilities = listOf("limber", "imposter")
                )
            ),
            isFavorite = false,
            onBackClick = {},
            onFavoriteToggle = {}
        )
    }
}
