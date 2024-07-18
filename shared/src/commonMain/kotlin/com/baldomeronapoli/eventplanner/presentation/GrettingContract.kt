package com.baldomeronapoli.eventplanner.presentation

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

    sealed interface UiIntent : BaseUiIntent {
        data object LoadGreeting : UiIntent
        data object OnDecreaseCountClick : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object ShowCountCanNotBeNegativeToast : Effect
    }
}