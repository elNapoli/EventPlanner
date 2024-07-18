package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.mvi.BaseSideEffect
import com.baldomeronapoli.eventplanner.mvi.BaseUiAction
import com.baldomeronapoli.eventplanner.mvi.BaseUiSate

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

    sealed interface UiAction : BaseUiAction {
        data object ToggleVisualTransformation : UiAction
        data class SaveEmail(val email: String) : UiAction
        data class SavePassword(val password: String) : UiAction
    }

    sealed interface SideEffect : BaseSideEffect {
        data object ShowCountCanNotBeNegativeToast : SideEffect
    }
}

