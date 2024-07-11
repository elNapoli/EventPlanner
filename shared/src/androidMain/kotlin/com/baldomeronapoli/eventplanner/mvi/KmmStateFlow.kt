package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

actual class KmmStateFlow<T> actual constructor(
    private val source: StateFlow<T>
) : StateFlow<T> by source {
    actual override val value: T
        get() = source.value

    actual override suspend fun collect(collector: FlowCollector<T>): Nothing {
        source.collect(collector)
    }

    actual override val replayCache: List<T>
        get() = source.replayCache

}
