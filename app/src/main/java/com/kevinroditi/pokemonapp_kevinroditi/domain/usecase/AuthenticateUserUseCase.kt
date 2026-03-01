package com.kevinroditi.pokemonapp_kevinroditi.domain.usecase

import androidx.fragment.app.FragmentActivity
import com.kevinroditi.pokemonapp_kevinroditi.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * UseCase to authenticate the user.
 * Independent of whether it's biometric or Firebase.
 */
class AuthenticateUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(activity: FragmentActivity): Result<Unit> {
        return repository.authenticate(activity)
    }
}
