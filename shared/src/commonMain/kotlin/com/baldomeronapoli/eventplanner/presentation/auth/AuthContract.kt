package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.ErrorDialog
import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import com.baldomeronapoli.eventplanner.utils.ValidateState

interface AuthContract {
    data class UiState(
        var passwordVisible: Boolean,
        @property:EmailValidation
        var email: String,
        var userId: String?,
        var password: String,
        var repeatPassword: String,
        var loading: Boolean,
        var error: ValidationError?
    ) : BaseUiState() {
        // NOTE: de debe instanciar el constructor de esta forma para KMM
        constructor() : this(
            passwordVisible = false,
            email = "",
            userId = null,
            password = "",
            repeatPassword = "",
            loading = false,
            error = null
        )

        fun validateProperties(): UiState {
            val stateValidator = ValidateState(UiState::class)
            val error = stateValidator.validate(this)
            return copy(error = error)
        }

        fun loading(value: Boolean): UiState = copy(loading = value)
        fun saveUserId(value: String): UiState = copy(userId = value)
        fun saveEmail(email: String): UiState = copy(email = email)
        fun savePassword(password: String): UiState = copy(password = password)
        fun saveRepeatPassword(repeatPassword: String): UiState =
            copy(repeatPassword = repeatPassword)

        fun togglePasswordVisible(): UiState = copy(passwordVisible = !passwordVisible)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object ToggleVisualTransformation : UiIntent
        data class SaveEmail(val email: String) : UiIntent
        data class SavePassword(val password: String) : UiIntent
        data class SaveRepeatPassword(val repeatPassword: String) : UiIntent
        data object CreateUseWithEmailAndPassword : UiIntent
        data object SignInWithEmailAndPassword : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data class ShowAlert(val errorDialog: ErrorDialog) : Effect
        data object GoToHome : Effect
        data object None : Effect
    }
}

