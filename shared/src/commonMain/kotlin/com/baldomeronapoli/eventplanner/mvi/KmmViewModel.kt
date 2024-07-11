package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect open class KmmViewModel<STATE : KmmState, EVENT : KmmEvent>(initialState: STATE) {

    protected val scope: CoroutineScope
    protected val _state: MutableStateFlow<STATE>
    val state: KmmStateFlow<STATE>

}