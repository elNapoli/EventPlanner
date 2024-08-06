package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUIType

@Composable
fun AlertDialog(
    onDismissRequest: (() -> Unit)? = null,
    onConfirmation: () -> Unit,
    feedbackUIType: FeedbackUIType = FeedbackUIType.SUCCESS,
    confirmText: String,
    dismissText: String? = null,
    dialogTitle: String,
    dialogText: String,
) {
    val icon = when (feedbackUIType) {
        FeedbackUIType.SUCCESS -> Icons.Default.CheckCircle
        FeedbackUIType.ERROR -> Icons.Default.Cancel
        FeedbackUIType.WARNING -> Icons.Default.Info
    }
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = null)
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest?.invoke()
        },
        confirmButton = {
            NTextButton(
                text = confirmText,
                onClick = {
                    onConfirmation()
                }
            )
        },
        dismissButton = {
            onDismissRequest?.let {
                NTextButton(
                    text = dismissText ?: "Cancelar",
                    onClick = {
                        onDismissRequest.invoke()
                    }
                )
            }

        }
    )
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAlertDialogLight(modifier: Modifier = Modifier) {
    NPreview {

        AlertDialog(
            onConfirmation = { },
            dialogTitle = "Titulo",
            dialogText = "esto es un mensaje de feedback al usuario ",
            confirmText = stringResource(id = R.string.login_button),
        )
    }
}