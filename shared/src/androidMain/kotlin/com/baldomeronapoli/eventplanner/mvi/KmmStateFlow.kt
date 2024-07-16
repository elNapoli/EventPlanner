package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KmmStateFlow<T> actual constructor(
    private val source: StateFlow<T>
) : StateFlow<T> {
    actual override val value: T = source.value
    actual override val replayCache: List<T> = source.replayCache

    actual override suspend fun collect(collector: FlowCollector<T>): Nothing {
        source.collect(collector)
    }
}