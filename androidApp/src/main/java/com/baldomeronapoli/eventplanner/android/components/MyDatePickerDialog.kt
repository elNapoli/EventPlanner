package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.baldomeronapoli.eventplanner.android.R
import java.time.Instant
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    show: Boolean = false,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
    value: Long
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= System.currentTimeMillis()
            }
        })
    if (show) {
        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                NButton(text = stringResource(id = R.string.date_picker_dialog_confirm)) {
                    val selectedDateUtc = datePickerState.selectedDateMillis!!
                    val selectedDateWithOffset = adjustToLocalTime(selectedDateUtc)
                    onDateSelected(selectedDateWithOffset)
                    onDismiss()
                }
            },
            dismissButton = {
                NButton(text = stringResource(id = R.string.date_picker_dialog_cancel)) {
                    onDismiss()
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }
    }
}

fun adjustToLocalTime(timeInMillisUTC: Long): Long {
    val instant = Instant.ofEpochMilli(timeInMillisUTC)
    val localZoneId = ZoneId.systemDefault()
    val offset = localZoneId.rules.getOffset(instant).totalSeconds / 3600
    return timeInMillisUTC + (-1 * offset * 3600 * 1000)
}
