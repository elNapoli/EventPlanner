package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.baldomeronapoli.eventplanner.domain.models.AlertDialogType

@Composable
fun AlertDialog(
    onDismissRequest: (() -> Unit)? = null,
    onConfirmation: () -> Unit,
    alertDialogType: AlertDialogType = AlertDialogType.SUCCESS,
    confirmText: String,
    dismissText: String? = null,
    dialogTitle: String,
    dialogText: String,
) {
    val icon = when (alertDialogType) {
        AlertDialogType.SUCCESS -> Icons.Default.CheckCircle
        AlertDialogType.ERROR -> Icons.Default.Cancel
        AlertDialogType.WARNING -> Icons.Default.Info
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