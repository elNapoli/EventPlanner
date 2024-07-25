package com.baldomeronapoli.eventplanner.android.views.auth

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.AlertDialog
import com.baldomeronapoli.eventplanner.android.components.CollectEffect
import com.baldomeronapoli.eventplanner.android.components.DividerWithText
import com.baldomeronapoli.eventplanner.android.components.LoadingWrapper
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUIType
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    goBack: () -> Unit
) {
    EmptyScaffold {
        CollectEffect(effect) {
            when (it) {
                Effect.GoToHome -> {
                    Logger.d("No se implementa en esta vista....")
                }

                Effect.None -> TODO()
            }
        }
        if (uiState.feedbackUI != null) {
            AlertDialog(
                onConfirmation = {
                    if (uiState.feedbackUI!!.type == FeedbackUIType.ERROR) onIntent(UiIntent.ResetFeedbackUI) else goBack()
                },
                dialogTitle = uiState.feedbackUI!!.title,
                dialogText = uiState.feedbackUI!!.message,
                feedbackUIType = uiState.feedbackUI!!.type,
                confirmText = if (uiState.feedbackUI!!.type == FeedbackUIType.ERROR) stringResource(
                    id = R.string.cancel
                ) else stringResource(
                    id = R.string.login_button
                ),
            )
        }

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (column1, column2, title, loading) = createRefs()
            Text(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = stringResource(id = R.string.create_your_account),
                style = MaterialTheme.typography.headlineLarge,
                color = GrayTitle,
                fontWeight = FontWeight.Bold
            )
            LoadingWrapper(modifier = Modifier.constrainAs(loading) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }, isLoading = uiState.loading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .constrainAs(column1) {
                            top.linkTo(title.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NOutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.login_with_google)
                    ) {

                    }
                    NOutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.login_with_facebook)
                    ) {

                    }
                    DividerWithText(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = stringResource(id = R.string.or)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(column2) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(column1.bottom)
                        }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    NOutlinedTextField(
                        value = uiState.email,
                        maxLines = 1,
                        onValueChange = { onIntent(UiIntent.SaveEmail(it)) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null
                            )
                        },
                        isError = uiState.error?.property == "email",
                        textError = uiState.error?.message ?: "",
                        placeholder = "prueba@gmail.com",
                        label = { Text(stringResource(id = R.string.email)) })

                    NOutlinedTextField(
                        value = uiState.password,
                        onValueChange = {
                            onIntent(UiIntent.SavePassword(it))
                        },
                        trailingIcon = {
                            val image = if (uiState.passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            IconButton(onClick = { onIntent(UiIntent.ToggleVisualTransformation) }) {
                                Icon(imageVector = image, null)
                            }
                        },
                        visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(stringResource(id = R.string.password)) })

                    NOutlinedTextField(
                        value = uiState.repeatPassword,
                        onValueChange = {
                            onIntent(UiIntent.SaveRepeatPassword(it))
                        },
                        isError = uiState.error?.property == "repeatPassword",
                        textError = uiState.error?.message ?: "",
                        trailingIcon = {
                            val image = if (uiState.passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            IconButton(onClick = { onIntent(UiIntent.ToggleVisualTransformation) }) {
                                Icon(imageVector = image, null)
                            }
                        },
                        visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = { Text(stringResource(id = R.string.repeat_password)) })
                    NButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        enabled = uiState.error == null,
                        text = stringResource(id = R.string.signup)
                    ) {
                        onIntent(UiIntent.CreateUseWithEmailAndPassword)

                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 45.dp, bottom = 32.dp)
                            .clickable { goBack() },
                        text = stringResource(id = R.string.i_am_already_account),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewSignUpScreenLight(modifier: Modifier = Modifier) {
    val effect: StateFlow<Effect?> = MutableStateFlow(null)
    NPreview {
        SignUpScreen(
            uiState = UiState(),
            effect = effect,
            onIntent = { },
            goBack = {}
        )
    }
}