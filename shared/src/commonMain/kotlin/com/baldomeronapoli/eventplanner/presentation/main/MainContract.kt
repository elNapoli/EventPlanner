package com.baldomeronapoli.eventplanner.presentation.main

import com.baldomero.napoli.eventplanner.core.presentation.effect.BaseUiIntent
import com.baldomero.napoli.eventplanner.core.presentation.intent.BaseEffect
import com.baldomero.napoli.eventplanner.core.presentation.state.BaseUiState
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.models.UserUI


interface MainContract {
    data class UiState(
        var currentEvent: EventUI? = null,
        var user: UserUI? = null,
        var loading: Boolean = false,
        var feedbackUI: FeedbackUI? = null
    ) : BaseUiState()

    sealed interface UiIntent : BaseUiIntent {
        data class SetCurrentEvent(val event: EventUI) : UiIntent
        data object CheckIsLoggedUser : UiIntent

    }

    sealed interface Effect : BaseEffect {
        data object GoToHome : Effect
    }
}

