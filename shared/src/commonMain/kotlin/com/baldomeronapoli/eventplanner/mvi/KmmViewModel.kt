package com.baldomeronapoli.eventplanner.mvi

import com.baldomeronapoli.eventplanner.domain.usecases.CoroutinesUseCaseRunner
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class KmmViewModel<STATE : KmmState, EVENT : KmmEvent>(initialState: STATE) :
    CoroutinesUseCaseRunner {

    override val scope: CoroutineScope
    protected val _state: MutableStateFlow<STATE>
    val state: KmmStateFlow<STATE>
    val loadingManager: LoadingManager
    val errorManager: ErrorManager

    protected val eventChannel: Channel<EVENT>
    protected val eventsFlow: Flow<EVENT>

    protected abstract fun observeEvents()

    protected fun sendEvent(event: EVENT)
    override fun <T> withUseCaseScope(
        loadingUpdater: ((Boolean) -> Unit)?,
        onError: ((Throwable) -> Unit)?,
        onSuccess: ((data: T) -> Unit),
        useCase: suspend () -> Flow<NetworkResult<T>>,
    )


    protected fun initialize()

}