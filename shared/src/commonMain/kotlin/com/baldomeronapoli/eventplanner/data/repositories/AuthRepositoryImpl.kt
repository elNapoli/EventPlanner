package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.data.utils.InitialRoute
import com.baldomeronapoli.eventplanner.domain.repositories.AuthRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient,
    private val preferences: SharePreferences
) :
    AuthRepository {
    override suspend fun createUseWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<NetworkResult<UserInfo?>> = flow {
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

    override suspend fun checkIsLoggedUserUseCase(): Flow<NetworkResult<UserInfo?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.awaitInitialization()
            val user = supabaseClient.auth.currentUserOrNull()

            if (user != null) {
                preferences.setInitialRoute(InitialRoute.HOME)
            }
            emit(NetworkResult.Success(user))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }
    }

    @NativeCoroutines
    override suspend fun loginWithGoogle(
        token: String,
        rawNonce: String
    ): Flow<NetworkResult<UserInfo?>> = flow {
        emit(NetworkResult.Loading(true))
        try {
            supabaseClient.auth.signInWith(IDToken) {
                idToken = token
                provider = Google
                nonce = rawNonce
            }
            emit(NetworkResult.Success(supabaseClient.auth.currentUserOrNull()))

        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))

        }
    }
}
