package com.kevinroditi.pokemonapp_kevinroditi.core.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

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
        val dominant = palette.getDominantColor(0xFFFFFF)
        return Color(dominant)
    }
}
