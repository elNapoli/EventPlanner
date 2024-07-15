package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.mvi.BaseViewModel
import com.baldomeronapoli.eventplanner.utils.useCaseRunner

class GreetingViewModel(
    private val getGreetingUseCase: GetGreetingUseCase,
) : BaseViewModel<GreetingContract.UiState, GreetingContract.UiAction, GreetingContract.SideEffect>(
    GreetingContract.UiState.initialUiState()
) {

    private fun getGreeting() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { copy(isLoading = value) } },
        onError = { },
        onSuccess = { data -> updateUiState { copy(data = data) } },
        useCase = { getGreetingUseCase() }
    )

    override fun onAction(uiAction: GreetingContract.UiAction) {
        when (uiAction) {
            GreetingContract.UiAction.OnDecreaseCountClick -> {}
            GreetingContract.UiAction.LoadGreeting -> getGreeting()
        }
    }
}