package com.baldomeronapoli.eventplanner.data.firebaseModels

import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.Serializable

@Serializable
data class FEvent(
    val id: String,
    var thumbnail: String,
    var title: String,
    var description: String,
    var hostId: String,
    var attendeesId: List<String>,
    var slots: Int,
    var date: String,
    var isPrivate: Boolean = false,
    var price: Double = 0.0,
) : Mappable<Event> {
    override fun map(): Event = Event(
        id = id,
        thumbnail = null,
        thumbnailUrl = thumbnail,
        title = title,
        description = description,
        boardgames = emptyList(),
        slots = slots,
        date = date,
        isPrivate = isPrivate,
        price = price,
        attendees = emptyList(),
    )
}