package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kevinroditi.pokemonapp_kevinroditi.R
import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter

@Composable
fun TypeChip(type: String) {
    val typeLabel = when (type.lowercase()) {
        "fire" -> stringResource(R.string.type_fire)
        "water" -> stringResource(R.string.type_water)
        "electric" -> stringResource(R.string.type_electric)
        "grass" -> stringResource(R.string.type_grass)
        "psychic" -> stringResource(R.string.type_psychic)
        "dragon" -> stringResource(R.string.type_dragon)
        "dark" -> stringResource(R.string.type_dark)
        "fairy" -> stringResource(R.string.type_fairy)
        "ghost" -> stringResource(R.string.type_ghost)
        "steel" -> stringResource(R.string.type_steel)
        "ice" -> stringResource(R.string.type_ice)
        "rock" -> stringResource(R.string.type_rock)
        "ground" -> stringResource(R.string.type_ground)
        "poison" -> stringResource(R.string.type_poison)
        "flying" -> stringResource(R.string.type_flying)
        "bug" -> stringResource(R.string.type_bug)
        "normal" -> stringResource(R.string.type_normal)
        "fighting" -> stringResource(R.string.type_fighting)
        else -> type.capitalizeFirstLetter()
    }

    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = Color.Black.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))
    ) {
        Text(
            text = typeLabel,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
