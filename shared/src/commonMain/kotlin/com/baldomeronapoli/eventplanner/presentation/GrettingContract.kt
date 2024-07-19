package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiSate
import kotlinx.serialization.Serializable

interface GreetingContract {
    @Serializable
    data class UiState(
        val data: String,
        val isLoading: Boolean,
        val errorMessage: String?
    ) : BaseUiSate {
        companion object {
            fun initialUiState() = UiState(
                data = "",
                isLoading = false,
                errorMessage = null
            )
        }

        fun loading(value: Boolean) = copy(isLoading = value)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object LoadGreeting : UiIntent
        data object OnDecreaseCountClick : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object ShowCountCanNotBeNegativeToast : Effect
    }
}