package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.mvi.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.SideEffect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiAction
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.utils.ValidateState

class AuthViewModel : BaseViewModel<UiState, UiAction, SideEffect>(
    UiState.initialUiState()
) {

    override fun onAction(uiAction: UiAction) {
        when (uiAction) {
            UiAction.ToggleVisualTransformation -> updateUiState { copy(passwordVisible = !uiState2.value.passwordVisible) }
            is UiAction.SaveEmail -> {
                updateUiState {
                    copy(email = uiAction.email)
                }
                val stateValidator = ValidateState(UiState::class)
                val error = stateValidator.validate(uiState2.value)
                updateUiState {
                    copy(error = error)
                }

            }

            is UiAction.SavePassword -> updateUiState {
                copy(password = uiAction.password)
            }
        }
    }
}