package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return repository.isUserLoggedIn()
    }
}
