package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiSate

interface AuthContract {
    data class UiState(
        val passwordVisible: Boolean,
        @property:EmailValidation
        val email: String,
        val password: String,
        val error: ValidationError? = null
    ) : BaseUiSate {
        companion object {
            fun initialUiState() = UiState(
                email = "",
                password = "",
                passwordVisible = false,
            )
        }
    }

    sealed interface UiIntent : BaseUiIntent {
        data object ToggleVisualTransformation : UiIntent
        data class SaveEmail(val email: String) : UiIntent
        data class SavePassword(val password: String) : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object ShowCountCanNotBeNegativeToast : Effect
    }
}

