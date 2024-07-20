package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.theme.Gray40

@Composable
fun NOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textError: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = OutlinedTextFieldDefaults.shape,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            shape = shape,
            isError = isError,
            supportingText = supportingText,
            prefix = prefix,
            suffix = suffix,
            trailingIcon = if (isError) {
                {
                    Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null)
                }
            } else if (trailingIcon != null) {
                {
                    trailingIcon()
                }
            } else null,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = OutlinedTextFieldDefaults.colors().copy(
                unfocusedLabelColor = Gray40,
                focusedPlaceholderColor = Gray40,
                errorTextColor = MaterialTheme.colorScheme.onError,
                errorLeadingIconColor = MaterialTheme.colorScheme.onError,
                errorTrailingIconColor = MaterialTheme.colorScheme.onError,
                errorLabelColor = MaterialTheme.colorScheme.onError,
                errorIndicatorColor = MaterialTheme.colorScheme.onError

            ),
            minLines = minLines,
        )
        if (isError) {
            textError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onError
                )
            }

        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewNOutlinedTextField() {
    NPreview {
        NOutlinedTextField(
            value = "prueba ",
            onValueChange = {},
            isError = false,
            textError = "asdf"
        )
        NOutlinedTextField(
            value = "prueba ",
            onValueChange = {},
            isError = true,
            textError = "asdf"
        )
    }
}