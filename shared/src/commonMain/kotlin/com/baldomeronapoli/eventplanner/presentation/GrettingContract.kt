package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.mvi.BaseSideEffect
import com.baldomeronapoli.eventplanner.mvi.BaseUiAction
import com.baldomeronapoli.eventplanner.mvi.BaseUiSate

data class caca(
    val data: String,
    val isLoading: Boolean,
    val errorMessage: String?
) {
    companion object {
        fun default() = caca(
            data = "",
            isLoading = false,
            errorMessage = null
        )
    }
}

val ho = GreetingContract.UiState.initialUiState()

interface GreetingContract {
    val hola: String

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