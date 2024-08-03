package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    @NativeCoroutines
    suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<User?>>

    @NativeCoroutines
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>>

    @NativeCoroutines
    suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<User?>>

    @NativeCoroutines
    suspend fun loginWithGoogle(token: String, rawNonce: String): Flow<NetworkResult<User?>>

}