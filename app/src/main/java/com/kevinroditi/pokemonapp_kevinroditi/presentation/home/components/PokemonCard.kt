package com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Pokemon

/**
 * PokemonCard displaying the Pokemon's image, ID, and name with favorite toggle
 */
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 120),
        label = "card_scale_animation"
    )

    // Heart animation
    val heartScale by animateFloatAsState(
        targetValue = if (pokemon.isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = 0.5f, stiffness = 200f),
        label = "heart_scale_animation"
    )

    val heartColor by animateColorAsState(
        targetValue = if (pokemon.isFavorite) Color.Red else Color.Gray,
        animationSpec = tween(300),
        label = "heart_color_animation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable(
                onClick = onClick,
                onClickLabel = "Open Pokemon Detail"
            ),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "#${pokemon.id}",
                    style = MaterialTheme.typography.labelMedium
                )
                
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = if (pokemon.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle favorite",
                    tint = heartColor,
                    modifier = Modifier.scale(heartScale).size(24.dp)
                )
            }
        }
    }
}
