package com.kevinroditi.pokemonapp_kevinroditi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Initializing Hilt for DI
 *  - Ensuring single instance graph across the entire app lifecycle.
 *  - Lightweight performance
 */

@HiltAndroidApp
class PokeAppApplication : Application()
