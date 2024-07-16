package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KmmFlow<T> actual constructor(private val source: Flow<T>) : Flow<T> {
    actual override suspend fun collect(collector: FlowCollector<T>) {
        source.collect(collector)
    }
}
