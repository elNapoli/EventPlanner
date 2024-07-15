package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class KmmViewModel {
    actual val scope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
}