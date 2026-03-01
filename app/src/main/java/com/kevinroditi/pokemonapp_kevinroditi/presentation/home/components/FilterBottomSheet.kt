package com.kevinroditi.pokemonapp_kevinroditi.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kevinroditi.pokemonapp_kevinroditi.R
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
        "fire" to R.string.type_fire,
        "water" to R.string.type_water,
        "electric" to R.string.type_electric,
        "grass" to R.string.type_grass,
        "psychic" to R.string.type_psychic,
        "dragon" to R.string.type_dragon,
        "dark" to R.string.type_dark,
        "fairy" to R.string.type_fairy,
        "ghost" to R.string.type_ghost,
        "steel" to R.string.type_steel,
        "ice" to R.string.type_ice,
        "rock" to R.string.type_rock,
        "ground" to R.string.type_ground,
        "poison" to R.string.type_poison,
        "flying" to R.string.type_flying,
        "bug" to R.string.type_bug,
        "normal" to R.string.type_normal,
        "fighting" to R.string.type_fighting
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.filters), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Favorites toggle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onPreferencesChange(preferences.copy(onlyFavorites = !preferences.onlyFavorites))
                }
        ) {
            Checkbox(
                checked = preferences.onlyFavorites,
                onCheckedChange = { onPreferencesChange(preferences.copy(onlyFavorites = it)) }
            )
            Text(text = stringResource(R.string.only_favorites))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.type), style = MaterialTheme.typography.titleMedium)
        LazyRow(
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                FilterChip(
                    selected = preferences.typeFilter == null,
                    onClick = { onPreferencesChange(preferences.copy(typeFilter = null)) },
                    label = { Text(stringResource(R.string.all)) }
                )
            }
            items(pokemonTypes) { (typeKey, resId) ->
                FilterChip(
                    selected = preferences.typeFilter == typeKey,
                    onClick = { onPreferencesChange(preferences.copy(typeFilter = typeKey)) },
                    label = { Text(stringResource(resId)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.sort_order), style = MaterialTheme.typography.titleMedium)
        Column {
            SearchSortOrder.values().forEach { order ->
                val labelResId = when (order) {
                    SearchSortOrder.DEFAULT -> R.string.sort_default
                    SearchSortOrder.ID_ASC -> R.string.sort_id_asc
                    SearchSortOrder.ID_DESC -> R.string.sort_id_desc
                    SearchSortOrder.NAME_ASC -> R.string.sort_name_asc
                    SearchSortOrder.NAME_DESC -> R.string.sort_name_desc
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPreferencesChange(preferences.copy(sortOrder = order))
                        }
                ) {
                    RadioButton(
                        selected = preferences.sortOrder == order,
                        onClick = { onPreferencesChange(preferences.copy(sortOrder = order)) }
                    )
                    Text(text = stringResource(labelResId))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onDismiss,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.apply))
        }
    }
}
