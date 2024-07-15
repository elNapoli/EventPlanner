package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect open class KmmViewModel() {
    protected val scope: CoroutineScope
}