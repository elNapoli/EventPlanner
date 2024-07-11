package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class KmmViewModel<STATE : KmmState, EVENT : KmmEvent>(initialState: STATE) {

    protected val scope: CoroutineScope
    protected val _state: MutableStateFlow<STATE>
    val state: KmmStateFlow<STATE>

    protected val eventChannel: Channel<EVENT>
    protected val eventsFlow: Flow<EVENT>

    protected abstract fun observeEvents()

    protected fun sendEvent(event: EVENT)


    protected fun initialize()

}