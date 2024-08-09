package com.baldomeronapoli.eventplanner.presentation.models

import com.baldomero.napoli.eventplanner.core.presentation.models.UserUI
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EventUI(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("thumbnail_id")
    var thumbnail: ThumbnailUI = ThumbnailUI(),
    @SerialName("start_date")
    var startDate: Long = Clock.System.now().toEpochMilliseconds(),
    @SerialName("end_date")
    var endDate: Long = Clock.System.now().toEpochMilliseconds(),
    @SerialName("title")
    var title: String = "",
    @SerialName("description")
    var description: String = "",
    @SerialName("slots")
    var slots: Int = 0,
    @SerialName("is_private")
    var isPrivate: Boolean = false,
    @SerialName("price")
    var price: Double = 0.0,
    @SerialName("users")
    var host: UserUI = UserUI(),
    @SerialName("events_attendees")
    var attendees: List<UserUI> = emptyList(),
    @SerialName("addresses")
    var address: AddressUI = AddressUI(),
    @SerialName("event_boardgames")
    var boardgames: List<BoardGameUI> = emptyList()
) : Mappable<Event> {
    override fun toInstance(): Event = Event(
        id = id,
        thumbnail = thumbnail.toInstance(),
        startDate = Instant.fromEpochMilliseconds(startDate),
        endDate = Instant.fromEpochMilliseconds(endDate),
        title = title,
        description = description,
        slots = slots,
        isPrivate = isPrivate,
        price = price,
        host = host.toInstance(),
        attendees = attendees.map { it.toInstance() },
        address = address.toInstance(),
        boardgames = boardgames.map { it.toInstance() }
    )
}