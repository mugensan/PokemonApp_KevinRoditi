package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kevinroditi.pokemonapp_kevinroditi.core.extensions.capitalizeFirstLetter

@Composable
fun TypeChip(type: String) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = Color.Black.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.1f))
    ) {
        Text(
            text = type.capitalizeFirstLetter(),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
