package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
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

    fun subscribe(onEach: (T) -> Unit, onCompletion: (Throwable?) -> Unit): KmmSubscription {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        source
            .onEach { onEach(it) }
            .catch { onCompletion(it) }
            .onCompletion { onCompletion(null) }
            .launchIn(scope)
        return KmmSubscription { scope.cancel() }
    }
}
