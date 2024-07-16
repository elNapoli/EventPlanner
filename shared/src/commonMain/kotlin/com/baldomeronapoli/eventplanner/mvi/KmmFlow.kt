package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class KmmFlow<T>(source: Flow<T>) : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>)
}