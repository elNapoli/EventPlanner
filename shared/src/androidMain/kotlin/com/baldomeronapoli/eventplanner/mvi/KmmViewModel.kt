package com.baldomeronapoli.eventplanner.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class KmmViewModel : ViewModel() {
    protected actual val scope: CoroutineScope
        get() = viewModelScope
}