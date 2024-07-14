package com.baldomeronapoli.eventplanner.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baldomeronapoli.eventplanner.domain.usecases.CoroutinesUseCaseRunner
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual abstract class KmmViewModel<STATE : KmmState, EVENT : KmmEvent> actual
constructor(initialState: STATE) :
    ViewModel(), CoroutinesUseCaseRunner {
    actual override val scope: CoroutineScope
        get() = viewModelScope
    actual val loadingManager = LoadingManager()
    actual val errorManager = ErrorManager()
    actual val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    actual val state: KmmStateFlow<STATE>
        get() = _state.asKmmStateFlow()
    protected actual val eventChannel: Channel<EVENT> = Channel(Channel.UNLIMITED)
    protected actual val eventsFlow: Flow<EVENT> = eventChannel.receiveAsFlow()

    protected actual abstract fun observeEvents()

    actual fun sendEvent(event: EVENT) {
        scope.launch {
            eventChannel.send(event)
        }
    }

    init {
        initialize()
    }

    protected actual fun initialize() {
        observeEvents()
    }

    actual override fun <T> withUseCaseScope(
        loadingUpdater: ((Boolean) -> Unit)?,
        onError: ((Throwable) -> Unit)?,
        onSuccess: ((data: T) -> Unit),
        useCase: suspend () -> Flow<NetworkResult<T>>,
    ) {
        val defaultLoadingUpdater = { isLoading: Boolean ->
            loadingManager.setLoading(isLoading)
        }
        super.withUseCaseScope(
            loadingUpdater = {
                loadingUpdater?.invoke(it) ?: defaultLoadingUpdater(it)
            },
            onError = {
                onError?.invoke(it) ?: errorManager.pushError(it)
            },
            onSuccess = onSuccess,
            useCase = useCase
        )
    }
}