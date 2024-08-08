package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomero.napoli.common.network.NetworkResult
import com.baldomeronapoli.eventplanner.data.postgresql.dto.map
import com.baldomeronapoli.eventplanner.data.postgresql.queries.EventQueries
import com.baldomeronapoli.eventplanner.data.utils.InitialRoute
import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import com.baldomeronapoli.eventplanner.utils.toMyError
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val eventQueries: EventQueries,
    private val preferences: SharePreferences
) : AuthRepository {
    override suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<User?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            val user = supabaseClient.auth.currentUserOrNull()

            emit(NetworkResult.Success(user.map()))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(error = e.toMyError()))
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<User?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val user = supabaseClient.auth.currentUserOrNull()

            emit(NetworkResult.Success(user.map()))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(error = e.toMyError()))
        }
    }

    override suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<User?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.awaitInitialization()
            val user = supabaseClient.auth.currentUserOrNull()

            if (user != null) {
                preferences.setInitialRoute(InitialRoute.HOME)
            }
            emit(NetworkResult.Success(user.map()))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(error = e.toMyError()))
        }
    }

    @NativeCoroutines
    override suspend fun loginWithGoogle(
        token: String,
        rawNonce: String
    ): Flow<NetworkResult<User?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.signInWith(IDToken) {
                idToken = token
                provider = Google
                nonce = rawNonce
            }
            val user = supabaseClient.auth.currentUserOrNull()

            emit(NetworkResult.Success(user.map()))

        } catch (e: Throwable) {
            emit(NetworkResult.Error(error = e.toMyError()))

        }
    }
}