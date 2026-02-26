package com.kevinroditi.pokemonapp_kevinroditi.core.extensions

/**
 * Capitalizing first letter safely
 */

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase()
        else it.toString()

    }
}