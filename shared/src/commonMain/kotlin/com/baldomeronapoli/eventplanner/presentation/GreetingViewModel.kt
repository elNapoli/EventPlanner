package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.Effect
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiState
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel

class GreetingViewModel(
    private val getGreetingUseCase: GetGreetingUseCase,
) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState()
) {

    private fun getGreeting() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = { sendEffect(Effect.ShowCountCanNotBeNegativeToast) },
        onSuccess = { data ->
            sendEffect(Effect.ShowCountCanNotBeNegativeToast)
            updateUiState { copy(data = data) }
        },
        useCase = { getGreetingUseCase() }
    )

    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.OnDecreaseCountClick -> {}
            UiIntent.LoadGreeting -> getGreeting()
        }
    }
}