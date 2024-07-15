package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.mvi.BaseSideEffect
import com.baldomeronapoli.eventplanner.mvi.BaseUiAction
import com.baldomeronapoli.eventplanner.mvi.BaseUiSate

interface GreetingContract {
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
    }

    sealed interface UiAction : BaseUiAction {
        data object LoadGreeting : UiAction
        data object OnDecreaseCountClick : UiAction
    }

    sealed interface SideEffect : BaseSideEffect {
        data object ShowCountCanNotBeNegativeToast : SideEffect
    }
}