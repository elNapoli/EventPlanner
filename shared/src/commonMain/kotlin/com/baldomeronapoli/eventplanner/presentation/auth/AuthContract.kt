package com.baldomeronapoli.eventplanner.presentation.auth

import com.baldomeronapoli.eventplanner.domain.models.ValidationError
import com.baldomeronapoli.eventplanner.domain.properties.EmailValidation
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.models.UserUI
import com.baldomeronapoli.eventplanner.shared.MySecrets
import com.baldomeronapoli.eventplanner.utils.ValidateState
import com.baldomeronapoli.eventplanner.utils.randomUUID
import io.ktor.utils.io.core.toByteArray
import okio.ByteString

interface AuthContract {
    data class UiState(
        var passwordVisible: Boolean,
        @property:EmailValidation
        var email: String,
        var user: UserUI?,
        var password: String,
        var repeatPassword: String,
        var googleClientId: String,
        var loading: Boolean,
        val nonce: String,
        var error: ValidationError?,
        var feedbackUI: FeedbackUI?
    ) : BaseUiState() {
        // NOTE: de debe instanciar el constructor de esta forma para KMM
        constructor() : this(
            passwordVisible = false,
            email = "",
            user = null,
            password = "",
            nonce = randomUUID,
            googleClientId = MySecrets.GOOGLE_CLIENT_ID,
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
            user: UserUI?,
            feedbackUI: FeedbackUI?
        ): UiState =
            copy(user = user, feedbackUI = feedbackUI)

        fun nonceHash(): String {
            val bytes = nonce.toByteArray()
            val hashedNonce = ByteString.of(*bytes).sha256().hex()
            return hashedNonce
        }

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
        data class LoginWithGoogle(val token: String) : UiIntent

    }

    sealed interface Effect : BaseEffect {
        data object GoToHome : Effect
        data object None : Effect
    }
}

