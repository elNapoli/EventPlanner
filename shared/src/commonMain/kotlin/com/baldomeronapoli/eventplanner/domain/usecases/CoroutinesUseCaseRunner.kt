package com.baldomeronapoli.eventplanner.domain.usecases

import com.baldomero.napoli.common.network.MyError
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.toMyError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface CoroutinesUseCaseRunner {
    val scope: CoroutineScope
    fun <T> withUseCaseScope(
        loadingUpdater: ((Boolean) -> Unit)? = null,
        onError: ((MyError) -> Unit)? = null,
        onSuccess: ((data: T) -> Unit),
        useCase: suspend () -> Flow<NetworkResult<T>>,
    ) {
        scope.launch {
            try {
                useCase().collect { result ->
                    when (result) {
                        is NetworkResult.Success -> onSuccess(result.data)
                        is NetworkResult.Loading -> loadingUpdater?.invoke(true)
                        is NetworkResult.Error -> onError?.invoke(result.error)
                    }

                }
            } catch (e: Exception) {
                onError?.invoke(e.toMyError())
            } finally {
                loadingUpdater?.invoke(false)
            }
        }
    }
}