package com.baldomeronapoli.eventplanner.android.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.theme.Purple
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.Effect
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onAction: (UiIntent) -> Unit,
    sideEffect: Flow<Effect>,
) {
    var a by remember {
        mutableStateOf("")
    }
    EmptyScaffold {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (column1, column2) = createRefs()
            Column(modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
                .constrainAs(column1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(

                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.headlineLarge,
                    color = GrayTitle,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.login_description),
                    style = MaterialTheme.typography.titleSmall,
                )
                NOutlinedTextField(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    value = uiState.email,
                    onValueChange = { onAction(UiIntent.SaveEmail(it)) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    isError = uiState.error?.property == "email",
                    textError = uiState.error?.message ?: "",
                    placeholder = { Text(text = "prueba@gmail.com") },
                    label = { Text(stringResource(id = R.string.email)) })

                NOutlinedTextField(
                    value = uiState.password,
                    onValueChange = {
                        onAction(UiIntent.SavePassword(it))
                    },
                    trailingIcon = {
                        val image = if (uiState.passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = { onAction(UiIntent.ToggleVisualTransformation) }) {
                            Icon(imageVector = image, null)
                        }
                    },
                    visualTransformation = if (uiState.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    label = { Text(stringResource(id = R.string.password)) })
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = MutableInteractionSource(),
                        ) {}
                        .fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Purple,
                    style = MaterialTheme.typography.bodyLarge,
                    text = stringResource(id = R.string.forgot_password)
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(column2) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.login)
                ) {

                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Línea horizontal izquierda
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.Gray
                    )

                    // Espacio y texto
                    Text(
                        text = stringResource(id = R.string.or_login_with),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = Color.Gray
                    )

                    // Línea horizontal derecha
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.Gray
                    )
                }
                Row {

                    Text(text = "asdf")
                    Text(text = "asdf")
                }
                Text(
                    modifier = Modifier.padding(top = 45.dp, bottom = 32.dp),
                    text = stringResource(id = R.string.you_dont_have_an_account),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewSignInScreenLight(modifier: Modifier = Modifier) {
    NPreview {
        SignInScreen(
            uiState = UiState.initialUiState(),
            sideEffect = flowOf(),
            onAction = { }
        )
    }
}