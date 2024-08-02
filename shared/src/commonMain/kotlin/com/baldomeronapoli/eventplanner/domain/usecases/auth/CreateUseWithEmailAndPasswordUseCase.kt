package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class CreateUseWithEmailAndPasswordUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(params: Params): Flow<NetworkResult<String?>> =
        repository.createUseWithEmailAndPassword(params.email, params.password)

    data class Params(val email: String, val password: String)
}
