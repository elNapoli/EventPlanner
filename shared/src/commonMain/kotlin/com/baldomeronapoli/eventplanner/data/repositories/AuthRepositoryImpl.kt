package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(private val auth: FirebaseAuth) : AuthRepository {
    override suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            val userId = result.user!!.uid
            auth.signOut()
            emit(NetworkResult.Success(userId))
        } catch (e: Exception) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }
}
