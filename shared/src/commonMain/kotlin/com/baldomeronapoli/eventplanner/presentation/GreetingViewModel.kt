package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.mvi.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.SideEffect
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiAction
import com.baldomeronapoli.eventplanner.presentation.GreetingContract.UiState
import com.baldomeronapoli.eventplanner.utils.useCaseRunner

class GreetingViewModel(
    private val getGreetingUseCase: GetGreetingUseCase,
) : BaseViewModel<UiState, UiAction, SideEffect>(
    UiState.initialUiState()
) {

    private fun getGreeting() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { copy(isLoading = value) } },
        onError = { emitSideEffect(SideEffect.ShowCountCanNotBeNegativeToast) },
        onSuccess = { data ->
            emitSideEffect(SideEffect.ShowCountCanNotBeNegativeToast)
            updateUiState { copy(data = data) }
        },
        useCase = { getGreetingUseCase() }
    )

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.OnDecreaseCountClick -> {}
            UiAction.LoadGreeting -> getGreeting()
        }
    }
}