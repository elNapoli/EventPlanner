package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class CheckIsLoggedUserUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(): Flow<NetworkResult<User?>> =
        repository.checkIsLoggedUserUseCase()
}
