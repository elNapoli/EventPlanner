package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(private val prefs: SharePreferences) :
    AuthRepository {
    override suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>> = flow {
        emit(NetworkResult.Loading(true))
        try {

            emit(NetworkResult.Success(null))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<String?>> = flow {
        emit(NetworkResult.Loading(true))
        try {

            emit(NetworkResult.Success(null))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }

    override suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            emit(NetworkResult.Success(false))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }
}
