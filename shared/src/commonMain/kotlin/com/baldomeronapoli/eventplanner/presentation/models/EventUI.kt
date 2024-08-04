package com.baldomeronapoli.eventplanner.presentation.models

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
    @SerialName("thumbnail")
    var thumbnail: String = "",
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
        id = this.id,
        thumbnail = this.thumbnail,
        startDate = Instant.fromEpochMilliseconds(this.startDate),
        endDate = Instant.fromEpochMilliseconds(this.endDate),
        title = this.title,
        description = this.description,
        slots = this.slots,
        isPrivate = this.isPrivate,
        price = this.price,
        host = this.host.toInstance(),
        attendees = this.attendees.map { it.toInstance() },
        address = this.address.toInstance(),
        boardgames = this.boardgames.map { it.toInstance() }
    )
}