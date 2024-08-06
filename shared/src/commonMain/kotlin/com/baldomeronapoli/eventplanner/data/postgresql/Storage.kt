package com.baldomeronapoli.eventplanner.data.postgresql

import com.baldomeronapoli.eventplanner.utils.randomUUID
import kotlinx.datetime.Clock

object Storage {
    const val BUCKET_NAME = "events"
    fun generatePathFile(eventId: Int): String {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        return "${eventId}/thumbnail_${timestamp}_$randomUUID.jpg"
    }
}