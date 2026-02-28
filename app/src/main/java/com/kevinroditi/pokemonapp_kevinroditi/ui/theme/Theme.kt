package com.kevinroditi.pokemonapp_kevinroditi.ui.theme

/**
 * Modern Material3 theme setup
 *
 * Why:
 * - Latest UI trend
 * - Better color system
 * - Dynamic theming ready
 */
@Composable
fun PokeAppApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}