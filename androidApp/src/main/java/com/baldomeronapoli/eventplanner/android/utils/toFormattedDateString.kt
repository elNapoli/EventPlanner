package com.baldomeronapoli.eventplanner.android.utils

import com.baldomeronapoli.eventplanner.utils.TimeConstant
import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toFormattedDateString(pattern: String = TimeConstant.DATE_FORMAT_SHORT): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(this)
}