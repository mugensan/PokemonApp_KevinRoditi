package com.kevinroditi.pokemonapp_kevinroditi

/**
 * Single activity architecture
 *
 * Why?
 * - Recommended by Google
 * - Works best with Compose
 * - Navigation handled via NavHost
 */

@AndroidEntryPoint
class PokeAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}
