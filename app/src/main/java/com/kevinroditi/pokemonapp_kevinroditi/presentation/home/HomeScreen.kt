package com.kevinroditi.pokemonapp_kevinroditi.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components.FilterBottomSheet
import com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components.PokemonGrid

/**
 * HomeScreen displaying paginated Pokemon grid with Search and Filters
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPokemonClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val pokemonPagingItems = viewModel.pokemonPagingData.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val preferences by viewModel.preferences.collectAsState()
    
    var showFilterSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent // Allow gradient background to show
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        ) {
            // App Title and Author Info - Centered and Dashing
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pokédex",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 4.dp),
                    textAlign = TextAlign.Center
                )
                
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f),
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/kevin-roditi-b2800b100?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"))
                            context.startActivity(intent)
                        }
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Created by ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)) {
                                append("Kevin Roditi")
                            }
                        },
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textAlign = TextAlign.Center
                    )
                }
            }

            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChange,
                onSearch = { /* Handled by debounce */ },
                active = false,
                onActiveChange = { },
                placeholder = { Text("Search by name, ID or type") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear search")
                            }
                        }
                        IconButton(onClick = { showFilterSheet = true }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "Filters")
                        }
                        IconButton(onClick = onFavoritesClick) {
                            Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .shadow(12.dp, MaterialTheme.shapes.extraLarge)
            ) {
            }

            PokemonGrid(
                pokemonPagingItems = pokemonPagingItems,
                onPokemonClick = onPokemonClick,
                onFavoriteClick = { pokemon -> viewModel.toggleFavorite(pokemon) },
                modifier = Modifier.weight(1f)
            )
        }

        if (showFilterSheet) {
            ModalBottomSheet(
                onDismissRequest = { showFilterSheet = false },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                FilterBottomSheet(
                    preferences = preferences,
                    onPreferencesChange = viewModel::updatePreferences,
                    onDismiss = { showFilterSheet = false }
                )
            }
        }
    }
}
