package com.kevinroditi.pokemonapp_kevinroditi.presentation.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kevinroditi.pokemonapp_kevinroditi.R
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.Stat

@Composable
fun StatBar(
    stat: Stat,
    color: Color,
    maxValue: Int = 150
) {
    var animationStarted by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animationStarted) stat.value / maxValue.toFloat() else 0f,
        animationSpec = tween(1000, 100),
        label = "stat_bar_animation"
    )

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    val statLabel = when (stat.name.lowercase()) {
        "hp" -> stringResource(R.string.stat_hp)
        "attack" -> stringResource(R.string.stat_attack)
        "defense" -> stringResource(R.string.stat_defense)
        "special-attack" -> stringResource(R.string.stat_special_attack)
        "special-defense" -> stringResource(R.string.stat_special_defense)
        "speed" -> stringResource(R.string.stat_speed)
        else -> stat.name
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statLabel,
            modifier = Modifier.weight(0.3f),
            style = MaterialTheme.typography.labelMedium
        )

        Box(
            modifier = Modifier
                .weight(0.7f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(color)
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Text(
            text = stat.value.toString(),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
