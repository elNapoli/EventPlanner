package com.baldomeronapoli.eventplanner.android.utils

import com.baldomero.napoli.common.formatters.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toFormattedDateString(pattern: String = TimeFormat.DATE_FORMAT_SHORT): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}