package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomero.napoli.common.network.NetworkResult
import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class CreateUseWithEmailAndPasswordUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(params: Params): Flow<NetworkResult<User?>> =
        repository.createUseWithEmailAndPassword(params.email, params.password)

    data class Params(val email: String, val password: String)
}
