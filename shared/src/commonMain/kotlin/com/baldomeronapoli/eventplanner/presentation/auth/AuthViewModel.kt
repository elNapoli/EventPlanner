package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.AlertDialogType
import com.baldomeronapoli.eventplanner.domain.models.ErrorDialog
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel

class AuthViewModel(
    private val createUseWithEmailAndPasswordUseCase: CreateUseWithEmailAndPasswordUseCase
) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState()
) {

    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.ToggleVisualTransformation -> updateUiState { copy(passwordVisible = !uiState.value.passwordVisible) }
            is UiIntent.SaveEmail -> {
                updateUiState {
                    saveEmail(uiIntent.email)
                }
                updateUiState {
                    validateProperties()
                }
            }

            is UiIntent.SavePassword -> {
                updateUiState {
                    savePassword(uiIntent.password)
                }
                updateUiState {
                    validateProperties()
                }
            }

            is UiIntent.SaveRepeatPassword -> {
                updateUiState { saveRepeatPassword(uiIntent.repeatPassword) }
            }

            UiIntent.CreateUseWithEmailAndPassword -> createUseWithEmailAndPassword()
        }
    }

    private fun createUseWithEmailAndPassword() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = { },
        onSuccess = { data ->
            updateUiState { saveUserId(data!!) }
            sendEffect(
                Effect.ShowAlert(
                    ErrorDialog(
                        "Cuenta creada",
                        "Tu cuenta ha sido creada exitosamente, el usuario es ${data}",
                        AlertDialogType.SUCCESS,
                        true
                    )
                )
            )
        },
        useCase = {
            createUseWithEmailAndPasswordUseCase(
                CreateUseWithEmailAndPasswordUseCase.Params(
                    email = uiState.value.email,
                    password = uiState.value.password
                )
            )
        }
    )
}