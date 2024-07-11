package com.baldomeronapoli.eventplanner.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual abstract class KmmViewModel<STATE : KmmState, EVENT : KmmEvent> actual constructor(private val initialState: STATE) :
    ViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope
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

}