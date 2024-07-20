package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    @NativeCoroutines
    suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>>

    @NativeCoroutines
    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<FirebaseUser?>>
}