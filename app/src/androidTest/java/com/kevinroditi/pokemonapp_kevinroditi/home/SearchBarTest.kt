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
class SearchBarTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun searchBar_typingUpdatesText() {
        val hint = composeTestRule.activity.getString(R.string.search_hint)
        composeTestRule.onNodeWithText(hint).performTextInput("Pikachu")
        composeTestRule.onNodeWithText("Pikachu").assertIsDisplayed()
    }

    @Test
    fun searchBar_clearButton_clearsInput() {
        val hint = composeTestRule.activity.getString(R.string.search_hint)
        composeTestRule.onNodeWithText(hint).performTextInput("Pikachu")
        
        // Find clear button (IconButton with description "Clear search")
        composeTestRule.onNodeWithContentDescription("Clear search").performClick()
        
        composeTestRule.onNodeWithText("Pikachu").assertDoesNotExist()
        composeTestRule.onNodeWithText(hint).assertIsDisplayed()
    }

    @Test
    fun searchBar_spanishHint_isVisible() {
        val expectedHint = "Buscar por nombre, ID o tipo (ej. fuego, agua, planta)"
        composeTestRule.onNodeWithText(expectedHint).assertIsDisplayed()
    }
}
