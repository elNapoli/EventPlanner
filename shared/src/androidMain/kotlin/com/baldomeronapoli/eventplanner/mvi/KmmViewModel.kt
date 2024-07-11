package com.baldomeronapoli.eventplanner.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class KmmViewModel<STATE : KmmState, EVENT : KmmEvent> actual constructor(private val initialState: STATE) :
    ViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope
    actual val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    actual val state: KmmStateFlow<STATE>
        get() = _state.asKmmStateFlow()
}