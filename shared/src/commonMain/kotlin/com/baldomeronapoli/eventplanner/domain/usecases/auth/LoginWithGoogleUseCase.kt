package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow


class LoginWithGoogleUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(token: String, rawNonce: String): Flow<NetworkResult<UserInfo?>> =
        repository.loginWithGoogle(token = token, rawNonce = rawNonce)
}
