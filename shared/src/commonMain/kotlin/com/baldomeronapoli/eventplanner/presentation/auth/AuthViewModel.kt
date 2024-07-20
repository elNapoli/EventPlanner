package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.AlertType
import com.baldomeronapoli.eventplanner.domain.models.ErrorDialog
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPassword
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel

class AuthViewModel(
    private val createUseWithEmailAndPasswordUseCase: CreateUseWithEmailAndPasswordUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPassword
) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState.initialUiState()
) {

    override fun handleIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            UiIntent.ToggleVisualTransformation -> updateUiState { togglePasswordVisible() }
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
            UiIntent.SignInWithEmailAndPassword -> signInWithEmailAndPassword()
        }
    }

    private fun createUseWithEmailAndPassword() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = { handleError(it) },
        onSuccess = { data ->
            updateUiState { saveUserId(data!!) }
            sendEffect(
                Effect.ShowAlert(
                    ErrorDialog(
                        "Cuenta creada",
                        "Tu cuenta ha sido creada exitosamente, el usuario es ${data}",
                        AlertType.SUCCESS,
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

    private fun signInWithEmailAndPassword() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = { handleError(it) },
        onSuccess = { data ->
            sendEffect(Effect.GoToHome)
        },
        useCase = {
            signInWithEmailAndPasswordUseCase(
                SignInWithEmailAndPassword.Params(
                    email = uiState.value.email,
                    password = uiState.value.password
                )
            )
        }
    )

    override fun handleError(e: Throwable) {
        sendEffect(
            Effect.ShowAlert(
                ErrorDialog(
                    "Error",
                    e.message!!,
                    AlertType.ERROR,
                )
            )
        )
    }
}