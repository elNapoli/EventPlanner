package com.baldomeronapoli.eventplanner.domain.usecases.auth

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow

class CreateUseWithEmailAndPasswordUseCase(
    private val repository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(params: Params): Flow<NetworkResult<UserInfo?>> =
        repository.createUseWithEmailAndPassword(params.email, params.password)

    data class Params(val email: String, val password: String)
}
