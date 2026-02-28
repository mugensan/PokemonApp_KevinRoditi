package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    name: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(name) {
        viewModel.loadPokemon(name)
    }

    val contentAlpha by animateFloatAsState(
        targetValue = if (state.pokemon != null) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "detail_fade_animation"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = name.capitalizeFirstLetter())
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
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
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.error != null -> {
                    Text(
                        text = state.error ?: "Unknown error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.pokemon != null -> {
                    val pokemon = state.pokemon!!

                    val backgroundColor = remember(pokemon.types) {
                        when (pokemon.types.firstOrNull()) {
                            "fire" -> Color(0xFFF08030)
                            "water" -> Color(0xFF6890F0)
                            "electric" -> Color(0xFFF8D030)
                            "grass" -> Color(0xFF78C850)
                            else -> MaterialTheme.colorScheme.primary
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor.copy(alpha = 0.15f))
                            .padding(24.dp)
                            .alpha(contentAlpha)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        AsyncImage(
                            model = pokemon.imageUrl,
                            contentDescription = pokemon.name,
                            modifier = Modifier
                                .size(220.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = pokemon.name.capitalizeFirstLetter(),
                            style = MaterialTheme.typography.headlineMedium,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Height: ${pokemon.height / 10f} m",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Weight: ${pokemon.weight / 10f} kg",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Types",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        pokemon.types.forEach { type ->
                            Text(
                                text = type.capitalizeFirstLetter(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
