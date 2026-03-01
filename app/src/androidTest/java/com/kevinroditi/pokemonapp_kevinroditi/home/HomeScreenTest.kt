package com.kevinroditi.pokemonapp_kevinroditi.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kevinroditi.pokemonapp_kevinroditi.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_displaysDashingTitle() {
        composeTestRule.onNodeWithText("Pokédex").assertIsDisplayed()
    }

    @Test
    fun homeScreen_searchBar_hasCorrectPlaceholder() {
        composeTestRule.onNodeWithText("Search by name, ID or type").assertIsDisplayed()
    }
}
