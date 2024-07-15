package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.StateFlow

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class KmmStateFlow<T> actual constructor(
    source: StateFlow<T>
) : StateFlow<T> by source