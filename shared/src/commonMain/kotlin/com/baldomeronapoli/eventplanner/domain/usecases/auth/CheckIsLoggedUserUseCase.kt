package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow

class CheckIsLoggedUserUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(): Flow<NetworkResult<UserInfo?>> =
        repository.checkIsLoggedUserUseCase()
}
