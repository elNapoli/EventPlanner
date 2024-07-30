package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.FeedbackUI
import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import com.baldomeronapoli.eventplanner.utils.ValidateState
import dev.gitlive.firebase.auth.FirebaseUser

interface AuthContract {
    data class UiState(
        var passwordVisible: Boolean,
        @property:EmailValidation
        var email: String,
        var user: FirebaseUser?,
        var password: String,
        var repeatPassword: String,
        var loading: Boolean,
        var error: ValidationError?,
        var feedbackUI: FeedbackUI?
    ) : BaseUiState() {
        // NOTE: de debe instanciar el constructor de esta forma para KMM
        constructor() : this(
            passwordVisible = false,
            email = "",
            user = null,
            password = "",
            repeatPassword = "",
            loading = false,
            error = null,
            feedbackUI = null
        )

        fun validateProperties(): UiState {
            val stateValidator = ValidateState(UiState::class)
            val error = stateValidator.validate(this)
            return copy(error = error)
        }

        fun loading(value: Boolean): UiState = copy(loading = value)
        fun saveEmail(email: String): UiState = copy(email = email)
        fun savePassword(password: String): UiState = copy(password = password)
        fun saveRepeatPassword(repeatPassword: String): UiState =
            copy(repeatPassword = repeatPassword)

        fun togglePasswordVisible(): UiState = copy(passwordVisible = !passwordVisible)

        fun handleCreateUseWithEmailAndPassword(
            user: FirebaseUser?,
            feedbackUI: FeedbackUI?
        ): UiState =
            copy(user = user, feedbackUI = feedbackUI)
    }

    sealed interface UiIntent : BaseUiIntent {
        data object ToggleVisualTransformation : UiIntent
        data object ResetFeedbackUI : UiIntent
        data class SaveEmail(val email: String) : UiIntent
        data class SavePassword(val password: String) : UiIntent
        data class SaveRepeatPassword(val repeatPassword: String) : UiIntent
        data object CreateUseWithEmailAndPassword : UiIntent
        data object SignInWithEmailAndPassword : UiIntent
        data object CheckIsLoggedUser : UiIntent
    }

    sealed interface Effect : BaseEffect {
        data object GoToHome : Effect
        data object None : Effect
    }
}

