package com.baldomeronapoli.eventplanner.utils

import kotlinx.coroutines.CoroutineScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class KmmViewModel() {
    protected val scope: CoroutineScope
}