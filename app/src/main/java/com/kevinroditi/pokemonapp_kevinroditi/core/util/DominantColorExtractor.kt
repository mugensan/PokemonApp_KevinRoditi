package com.kevinroditi.pokemonapp_kevinroditi.core.util

/**
 * Extract dominant color from bitmap
 *
 * Why:
 * - Dynamic UI based on Pokemon image
 * - Modern UI personalization trend
 */
object DominantColorExtractor {
    fun extractDominantColor(bitmap: Bitmap): Color {
        val palette = Palette.from(bitmap).generate()
        val dominant = Palette.getDominanteColor(0xFFFFFF)
        return Color(dominant)
    }
}