package com.baldomeronapoli.eventplanner.android.utils

import dev.gitlive.firebase.firestore.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun Timestamp.toMillis(): Long {
    return this.seconds * 1000 + this.nanoseconds / 1000000
}

fun Long.toTimestamp(): Timestamp {
    return Timestamp(this / 1000, ((this % 1000) * 1000000).toInt())
}

fun Timestamp.toFormattedDateString(format: String = "dd/MM/yyyy HH:mm"): String {
    val dateFormat = SimpleDateFormat(format, Locale.getDefault())
    return dateFormat.format(this.seconds * 1000L + this.nanoseconds / 1000000L)
}
