package com.kevinroditi.pokemonapp_kevinroditi

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.kevinroditi.pokemonapp_kevinroditi.presentation.navigation.AppNavGraph
import com.kevinroditi.pokemonapp_kevinroditi.ui.theme.PokeAppApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single activity architecture
 *
 * Why?
 * - Recommended by Google
 * - Works best with Compose
 * - Navigation handled via NavHost
 */

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppApplicationTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}
