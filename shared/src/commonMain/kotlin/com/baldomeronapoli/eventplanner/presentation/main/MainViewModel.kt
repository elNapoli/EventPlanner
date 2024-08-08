package com.baldomeronapoli.eventplanner.presentation.main

import co.touchlab.kermit.Logger
import com.baldomero.napoli.eventplanner.core.presentation.viewModel.BaseViewModel
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CheckIsLoggedUserUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.main.MainContract.Effect
import com.baldomeronapoli.eventplanner.presentation.main.MainContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.main.MainContract.UiState
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUIType

open class MainViewModel(
    private val checkIsLoggedUserUseCase: CheckIsLoggedUserUseCase,
) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState()
) {

    override fun sendIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            is UiIntent.SetCurrentEvent -> {
                Logger.e(uiIntent.event.toString())
                updateUiState { copy(currentEvent = uiIntent.event) }
            }

            UiIntent.CheckIsLoggedUser -> checkIsLoggedUser()
        }
    }


    private fun checkIsLoggedUser() = scope.useCaseRunner(
        loadingUpdater = { value ->
            updateUiState { copy(loading = value) }
        },
        onError = {
            updateUiState {
                copy(
                    user = null, feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message,
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }

        },
        onSuccess = { data ->
            data?.let { user ->
                updateUiState {
                    copy(
                        user = user.mapToUI()
                    )
                }
                sendEffect(Effect.GoToHome)
            }
        },
        useCase = {
            checkIsLoggedUserUseCase()
        }
    )
}