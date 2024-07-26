package com.baldomeronapoli.eventplanner.presentation.event

import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState

class EventContract


interface AuthContract {
    data class UiState(
        var event: Boolean,
    ) : BaseUiState() {
        // NOTE: de debe instanciar el constructor de esta forma para KMM
        constructor() : this(
            event = false,
        )
    }

    sealed interface UiIntent : BaseUiIntent

    sealed interface Effect : BaseEffect
}

