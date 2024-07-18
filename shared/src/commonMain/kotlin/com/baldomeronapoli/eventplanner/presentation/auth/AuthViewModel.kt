package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.presentation.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.utils.ValidateState

class AuthViewModel : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState()
) {

    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.ToggleVisualTransformation -> updateUiState { copy(passwordVisible = !uiState.value.passwordVisible) }
            is UiIntent.SaveEmail -> {
                updateUiState {
                    copy(email = uiIntent.email)
                }
                val stateValidator = ValidateState(UiState::class)
                val error = stateValidator.validate(uiState.value)
                updateUiState {
                    copy(error = error)
                }

            }

            is UiIntent.SavePassword -> updateUiState {
                copy(password = uiIntent.password)
            }
        }
    }
}