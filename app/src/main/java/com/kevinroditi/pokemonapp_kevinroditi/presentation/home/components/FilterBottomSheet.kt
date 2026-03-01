package com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchPreferences
import com.kevinroditi.pokemonapp_kevinroditi.domain.model.SearchSortOrder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    preferences: SearchPreferences,
    onPreferencesChange: (SearchPreferences) -> Unit,
    onDismiss: () -> Unit
) {
    val pokemonTypes = listOf(
        "fire", "water", "electric", "grass", "psychic", "dragon",
        "dark", "fairy", "ghost", "steel", "ice", "rock",
        "ground", "poison", "flying", "bug", "normal", "fighting"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Filters", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Favorites toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().clickable {
                onPreferencesChange(preferences.copy(onlyFavorites = !preferences.onlyFavorites))
            }
        ) {
            Checkbox(
                checked = preferences.onlyFavorites,
                onCheckedChange = { onPreferencesChange(preferences.copy(onlyFavorites = it)) }
            )
            Text(text = "Only Favorites")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Type", style = MaterialTheme.typography.titleMedium)
        LazyRow(
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = preferences.typeFilter == null,
                    onClick = { onPreferencesChange(preferences.copy(typeFilter = null)) },
                    label = { Text("All") }
                )
            }
            items(pokemonTypes) { type ->
                FilterChip(
                    selected = preferences.typeFilter == type,
                    onClick = { onPreferencesChange(preferences.copy(typeFilter = type)) },
                    label = { Text(type.replaceFirstChar { it.uppercase() }) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Sort Order", style = MaterialTheme.typography.titleMedium)
        Column {
            SearchSortOrder.values().forEach { order ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable {
                        onPreferencesChange(preferences.copy(sortOrder = order))
                    }
                ) {
                    RadioButton(
                        selected = preferences.sortOrder == order,
                        onClick = { onPreferencesChange(preferences.copy(sortOrder = order)) }
                    )
                    Text(text = order.name.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() })
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Apply")
        }
    }
}
