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
import com.baldomeronapoli.eventplanner.android.utils.toMillis
import com.baldomeronapoli.eventplanner.android.utils.toTimestamp
import dev.gitlive.firebase.firestore.Timestamp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDatePickerDialog(
    show: Boolean = false,
    value: Timestamp,
    onDateSelected: (Timestamp) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value.toMillis(),
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
                    onDateSelected(datePickerState.selectedDateMillis!!.toTimestamp())
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
