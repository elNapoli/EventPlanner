package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("thumbnail")
    var thumbnail: String,
    @SerialName("start_date")
    var startDate: String,
    @SerialName("end_date")
    var endDate: String,
    @SerialName("title")
    var title: String,
    @SerialName("description")
    var description: String,
    @SerialName("host_id")
    var hostId: String,
    @SerialName("slots")
    var slots: Int,
    @SerialName("is_private")
    var isPrivate: Boolean,
    @SerialName("price")
    var price: Double,
    @SerialName("users")
    var host: UserDTO,
    @SerialName("events_attendees")
    var attendees: List<EventAttendeeDTO>,
    @SerialName("addresses")
    var address: AddressDTO,
    @SerialName("event_boardgames")
    var boardgames: List<EventBoardGameDTO>
) : Mappable<Event> {
    override fun map(): Event = Event(
        id = id,
        thumbnail = thumbnail,
        startDate = startDate,
        endDate = endDate,
        title = title,
        description = description,
        slots = slots,
        isPrivate = isPrivate,
        price = price,
        host = host.map(),
        attendees = attendees.map { it.map() },
        address = address.map(),
        boardgames = boardgames.map { it.map() }
    )
}