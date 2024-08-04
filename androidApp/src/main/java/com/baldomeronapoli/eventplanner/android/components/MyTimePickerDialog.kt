package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.baldomeronapoli.eventplanner.android.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTimePickerDialog(
    show: Boolean = false,
    onTimeSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
    value: Long
) {
    if (show) {
        val currentTime = Calendar.getInstance().apply {
            timeInMillis = value
        }

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )
        AlertDialog(
            text = {
                TimePicker(
                    state = timePickerState,
                )
            },
            onDismissRequest = {
                onDismiss()
            },
            confirmButton = {
                NButton(text = stringResource(id = R.string.date_picker_dialog_confirm)) {
                    currentTime.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                    currentTime.set(Calendar.MINUTE, timePickerState.minute)
                    onTimeSelected(currentTime.timeInMillis)
                    onDismiss()
                }
            },
            dismissButton = {
                NButton(text = stringResource(id = R.string.date_picker_dialog_cancel)) {
                    onDismiss()
                }
            }
        )
    }
}
