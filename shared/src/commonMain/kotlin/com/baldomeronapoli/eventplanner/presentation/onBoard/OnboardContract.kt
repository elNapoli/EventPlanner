package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomero.napoli.eventplanner.core.presentation.effect.BaseUiIntent
import com.baldomero.napoli.eventplanner.core.presentation.intent.BaseEffect
import com.baldomero.napoli.eventplanner.core.presentation.state.BaseUiState
import kotlinx.serialization.Serializable

interface OnboardContract {
    @Serializable
    data class UiState(
        val showOnboarding: Boolean = true,
    ) : BaseUiState() {
        fun completeOnboarding(value: Boolean) = copy(showOnboarding = value)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object CompleteOnboarding : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object GoToAuthGraph : Effect
    }
}