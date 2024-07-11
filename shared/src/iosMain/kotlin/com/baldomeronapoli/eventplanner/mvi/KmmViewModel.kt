package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class KmmViewModel<STATE : KmmState, EVENT : KmmEvent> actual constructor(private val initialState: STATE) {
    protected actual val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCleared() {
        scope.cancel()
    }

    actual val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    actual val state: KmmStateFlow<STATE>
        get() = _state.asKmmStateFlow()
}