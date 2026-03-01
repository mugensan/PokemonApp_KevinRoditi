package com.kevinroditi.pokemonapp_kevinroditi.security.presentation

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Authenticated : AuthState()
    object Failed : AuthState()
    object NotAvailable : AuthState()
}
