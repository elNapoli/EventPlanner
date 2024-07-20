package com.baldomeronapoli.eventplanner.presentation.onBoard

import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiSate
import kotlinx.serialization.Serializable

interface OnboardContract {
    @Serializable
    data class UiState(
        val showOnboarding: Boolean,
    ) : BaseUiSate {
        companion object {
            fun initialUiState(showOnboarding: Boolean) = UiState(
                showOnboarding = showOnboarding,
            )
        }

        fun completeOnboarding(value: Boolean) = copy(showOnboarding = value)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object CompleteOnboarding : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object GoToAuthGraph : Effect
    }
}