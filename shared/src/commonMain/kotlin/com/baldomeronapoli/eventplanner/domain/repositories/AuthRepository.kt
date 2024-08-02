package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    @NativeCoroutines
    suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<UserInfo?>>

    @NativeCoroutines
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>>

    @NativeCoroutines
    suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<UserInfo?>>

    @NativeCoroutines
    suspend fun loginWithGoogle(token: String, rawNonce: String): Flow<NetworkResult<UserInfo?>>

}