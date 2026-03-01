package com.kevinroditi.pokemonapp_kevinroditi.security.presentation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.AuthenticateUserUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.CheckSessionUseCase
import com.kevinroditi.pokemonapp_kevinroditi.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val checkSessionUseCase: CheckSessionUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkInitialAuthState()
    }

    fun authenticate(activity: FragmentActivity) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = authenticateUserUseCase(activity)
            if (result.isSuccess) {
                _authState.value = AuthState.Authenticated
            } else {
                val message = result.exceptionOrNull()?.message ?: ""
                if (message.contains("NotAvailable", ignoreCase = true) || message.contains("not available", ignoreCase = true)) {
                    _authState.value = AuthState.NotAvailable
                } else {
                    _authState.value = AuthState.Failed
                }
            }
        }
    }

    private fun checkInitialAuthState() {
        viewModelScope.launch {
            if (checkSessionUseCase()) {
                _authState.value = AuthState.Authenticated
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _authState.value = AuthState.Idle
        }
    }
}
