package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomero.napoli.common.network.NetworkResult
import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow


class LoginWithGoogleUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(token: String, rawNonce: String): Flow<NetworkResult<User?>> =
        repository.loginWithGoogle(token = token, rawNonce = rawNonce)
}
