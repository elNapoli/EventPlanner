package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
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