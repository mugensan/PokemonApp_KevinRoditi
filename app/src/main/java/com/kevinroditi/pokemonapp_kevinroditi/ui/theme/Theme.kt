package com.kevinroditi.pokemonapp_kevinroditi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFE3350D), // Classic Pokémon Red
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD4),
    onPrimaryContainer = Color(0xFF3D0700),
    secondary = Color(0xFF3166B3), // Pokémon Blue
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD8E2FF),
    onSecondaryContainer = Color(0xFF001A41),
    tertiary = Color(0xFFFFCB05), // Pokémon Yellow
    onTertiary = Color(0xFF211B00),
    background = Color(0xFFFDFBFF),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1B1B1F),
    surfaceVariant = Color(0xFFF4F0F4),
    onSurfaceVariant = Color(0xFF49454F)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFB4A9),
    onPrimary = Color(0xFF690000),
    primaryContainer = Color(0xFF930000),
    onPrimaryContainer = Color(0xFFFFDAD4),
    secondary = Color(0xFFAEC6FF),
    onSecondary = Color(0xFF002E69),
    secondaryContainer = Color(0xFF004494),
    onSecondaryContainer = Color(0xFFD8E2FF),
    tertiary = Color(0xFFFFCB05),
    onTertiary = Color(0xFF211B00),
    background = Color(0xFF1B1B1F),
    surface = Color(0xFF201A1B),
    onSurface = Color(0xFFE3E2E6),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0)
)

@Composable
fun PokeAppApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
