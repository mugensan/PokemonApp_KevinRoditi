package com.kevinroditi.pokemonapp_kevinroditi.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kevinroditi.pokemonapp_kevinroditi.MainActivity
import com.kevinroditi.pokemonapp_kevinroditi.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FavoritesScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun favoritesScreen_clickFavoritesIcon_navigatesToFavorites() {
        // Navigate to Favorites from Home
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        
        // Check if we are on Favorites Screen using the string resource for "Favorites"
        val favoritesTitle = composeTestRule.activity.getString(R.string.favorites)
        composeTestRule.onNodeWithText(favoritesTitle).assertIsDisplayed()
    }

    @Test
    fun favoritesScreen_emptyState_showsNoFavoritesMessage() {
        // Navigate to Favorites
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        
        // Check for empty state message
        composeTestRule.onNodeWithText("No favorites yet").assertIsDisplayed()
    }
}
