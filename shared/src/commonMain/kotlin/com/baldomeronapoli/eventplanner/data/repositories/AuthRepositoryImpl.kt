package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(private val auth: FirebaseAuth, private val prefs: SharePreferences) :
    AuthRepository {
    override suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<FirebaseUser?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            val user = result.user!!

            auth.signOut()
            emit(NetworkResult.Success(user))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<FirebaseUser?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            val result = auth.signInWithEmailAndPassword(email, password)
            val userId = result.user!!
            prefs.setEmailCurrentUser(result.user!!.email)
            emit(NetworkResult.Success(userId))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }

    override suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            val user = auth.currentUser
            if (user != null) {
                prefs.setEmailCurrentUser(user.email)
                emit(NetworkResult.Success(true))

            } else {
                emit(NetworkResult.Success(false))
            }
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }
}
