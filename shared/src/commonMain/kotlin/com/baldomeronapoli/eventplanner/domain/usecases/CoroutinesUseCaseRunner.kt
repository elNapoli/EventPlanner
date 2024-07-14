package com.baldomeronapoli.eventplanner.domain.usecases

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface CoroutinesUseCaseRunner {
    val scope: CoroutineScope
    fun <T> withUseCaseScope(
        loadingUpdater: ((Boolean) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: ((data: T) -> Unit),
        useCase: suspend () -> Flow<NetworkResult<T>>,
    ) {
        scope.launch {
            try {
                useCase().collect { result ->
                    when (result) {
                        is NetworkResult.Success -> onSuccess(result.data)
                        is NetworkResult.Loading -> loadingUpdater?.invoke(true)
                        is NetworkResult.Error -> onError?.invoke(result.exception)
                    }

                }
            } catch (e: Exception) {
                onError?.invoke(e)
            } finally {
                loadingUpdater?.invoke(false)
            }
        }
    }
}