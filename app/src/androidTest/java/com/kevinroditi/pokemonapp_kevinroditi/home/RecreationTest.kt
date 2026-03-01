package com.kevinroditi.pokemonapp_kevinroditi.home

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ActivityScenario
import com.kevinroditi.pokemonapp_kevinroditi.MainActivity
import com.kevinroditi.pokemonapp_kevinroditi.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RecreationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun activityRecreation_preservesState() {
        // Given - Type something in search bar
        val hint = composeTestRule.activity.getString(R.string.search_hint)
        composeTestRule.onNodeWithText(hint).performTextInput("Pikachu")
        composeTestRule.onNodeWithText("Pikachu").assertIsDisplayed()

        // When - Recreate activity
        composeTestRule.activityRule.scenario.recreate()

        // Then - Verify text is still there
        composeTestRule.onNodeWithText("Pikachu").assertIsDisplayed()
    }

    @Test
    fun configurationChange_rotation_preservesState() {
        // Note: Simple recreation simulates config change in many aspects.
        // Direct orientation change is better done with UI Automator or ActivityScenario.
        
        val hint = composeTestRule.activity.getString(R.string.search_hint)
        composeTestRule.onNodeWithText(hint).performTextInput("Charizard")
        
        // Recreate to simulate config change
        composeTestRule.activityRule.scenario.recreate()
        
        composeTestRule.onNodeWithText("Charizard").assertIsDisplayed()
    }
}
