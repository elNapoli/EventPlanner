package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.ErrorDialog
import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.domain.properties.EqualsValidation
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiSate
import com.baldomeronapoli.eventplanner.utils.ValidateState

interface AuthContract {
    data class UiState(
        val passwordVisible: Boolean,
        @property:EmailValidation
        val email: String,
        val userId: String?,
        val password: String,
        @property:EqualsValidation(otherProperty = "password")
        val repeatPassword: String,
        val loading: Boolean,
        val error: ValidationError? = null
    ) : BaseUiSate {
        companion object {
            fun initialUiState() = UiState(
                email = "",
                password = "",
                repeatPassword = "",
                passwordVisible = false,
                loading = false,
                userId = null
            )
        }

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

        fun toggleVisualTransformation(): UiState = copy(passwordVisible = !passwordVisible)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object ToggleVisualTransformation : UiIntent
        data class SaveEmail(val email: String) : UiIntent
        data class SavePassword(val password: String) : UiIntent
        data class SaveRepeatPassword(val repeatPassword: String) : UiIntent
        data object CreateUseWithEmailAndPassword : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data class ShowAlert(val errorDialog: ErrorDialog) : Effect
    }
}

