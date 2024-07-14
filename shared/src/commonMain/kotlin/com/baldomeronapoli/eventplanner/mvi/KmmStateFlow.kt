package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KmmStateFlow<T>(source: StateFlow<T>) : StateFlow<T> {
    override val value: T
    override suspend fun collect(collector: FlowCollector<T>): Nothing
    override val replayCache: List<T>
}
