package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("thumbnail")
    var thumbnail: ThumbnailDto,
    @SerialName("start_date")
    var startDate: Instant,
    @SerialName("end_date")
    var endDate: Instant,
    @SerialName("title")
    var title: String,
    @SerialName("description")
    var description: String,
    @SerialName("slots")
    var slots: Int,
    @SerialName("is_private")
    var isPrivate: Boolean,
    @SerialName("price")
    var price: Double,
    @SerialName("host")
    var host: UserDTO,
    @SerialName("attendees")
    var attendees: List<UserDTO>,
    @SerialName("address")
    var address: AddressDTO,
    @SerialName("board_games")
    var boardgames: List<BoardGameDTO>
) : Mappable<Event> {
    override fun toInstance(): Event = Event(
        id = id,
        thumbnail = thumbnail.toInstance(),
        startDate = startDate,
        endDate = endDate,
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