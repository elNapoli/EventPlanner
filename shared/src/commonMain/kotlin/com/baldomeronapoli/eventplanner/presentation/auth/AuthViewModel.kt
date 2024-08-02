package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.FeedbackUI
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUIType
import com.baldomeronapoli.eventplanner.domain.models.toUserUI
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CheckIsLoggedUserUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.CreateUseWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.LoginWithGoogleUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.auth.SignInWithEmailAndPasswordUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel

open class AuthViewModel(
    private val createUseWithEmailAndPasswordUseCase: CreateUseWithEmailAndPasswordUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val checkIsLoggedUserUseCase: CheckIsLoggedUserUseCase,
    private val loginWithGoogleUseCase: LoginWithGoogleUseCase
) : BaseViewModel<UiState, UiIntent, Effect>(
    UiState()
) {

    override fun sendIntent(uiIntent: UiIntent) {
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

            is UiIntent.SaveRepeatPassword -> updateUiState { saveRepeatPassword(uiIntent.repeatPassword) }

            UiIntent.CreateUseWithEmailAndPassword -> createUseWithEmailAndPassword()
            UiIntent.SignInWithEmailAndPassword -> signInWithEmailAndPassword()
            UiIntent.ResetFeedbackUI -> updateUiState { copy(feedbackUI = null) }
            UiIntent.CheckIsLoggedUser -> checkIsLoggedUser()
            is UiIntent.LoginWithGoogle -> loginWithGoogle(uiIntent.token)
        }
    }

    private fun loginWithGoogle(token: String) = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = {
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = null, feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }
        },
        onSuccess = { data ->
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = data?.toUserUI(), feedbackUI = FeedbackUI(
                        title = "Cuenta creada",
                        message = "Tu cuenta ha sido creada exitosamente, el usuario es ${data?.email}",
                        type = FeedbackUIType.SUCCESS,
                        show = true
                    )
                )
            }
            sendEffect(Effect.GoToHome)
        },
        useCase = {
            loginWithGoogleUseCase(token = token, rawNonce = uiState.value.nonce)
        }
    )

    private fun createUseWithEmailAndPassword() = scope.useCaseRunner(
        loadingUpdater = { value -> updateUiState { loading(value) } },
        onError = {
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = null, feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }
        },
        onSuccess = { data ->
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = data?.toUserUI(), feedbackUI = FeedbackUI(
                        title = "Cuenta creada",
                        message = "Tu cuenta ha sido creada exitosamente, el usuario es ${data}",
                        type = FeedbackUIType.SUCCESS,
                        show = true
                    )
                )
            }
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
        loadingUpdater = { value ->
            updateUiState { loading(value) }
        },
        onError = {
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = null, feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }

        },
        onSuccess = { data ->
            sendEffect(Effect.GoToHome)
        },
        useCase = {
            signInWithEmailAndPasswordUseCase(
                SignInWithEmailAndPasswordUseCase.Params(
                    email = uiState.value.email,
                    password = uiState.value.password
                )
            )
        }
    )


    private fun checkIsLoggedUser() = scope.useCaseRunner(
        loadingUpdater = { value ->
            updateUiState { loading(value) }
        },
        onError = {
            updateUiState {
                handleCreateUseWithEmailAndPassword(
                    user = null, feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
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
                        user = user.toUserUI()
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