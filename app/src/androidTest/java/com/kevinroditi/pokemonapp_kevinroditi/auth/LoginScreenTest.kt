package com.kevinroditi.pokemonapp_kevinroditi.auth

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.kevinroditi.pokemonapp_kevinroditi.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun loginScreen_initialState_showsWelcomeMessageAndAuthenticateButton() {
        composeTestRule.onNodeWithText("Welcome to Pokédex").assertIsDisplayed()
        composeTestRule.onNodeWithText("Authenticate").assertIsDisplayed()
    }

    @Test
    fun loginScreen_clickAuthenticate_triggersAction() {
        composeTestRule.onNodeWithText("Authenticate").assertHasClickAction()
        composeTestRule.onNodeWithText("Authenticate").performClick()
    }
}
