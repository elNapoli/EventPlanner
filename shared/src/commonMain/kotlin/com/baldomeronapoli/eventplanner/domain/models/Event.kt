package com.baldomeronapoli.eventplanner.domain.models

import com.baldomero.napoli.eventplanner.core.domain.models.User
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName

data class Event(
    @SerialName("id")
    val id: Int,
    @SerialName("thumbnail")
    var thumbnail: Thumbnail,
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
    var host: User,
    @SerialName("attendees")
    var attendees: List<User>,
    @SerialName("address")
    var address: Address,
    @SerialName("board_games")
    var boardgames: List<BoardGame>
) : BiMappable<EventDTO, EventUI> {

    override fun mapToDto(): EventDTO = EventDTO(
        id = id,
        thumbnail = thumbnail.mapToDto(),
        startDate = startDate,
        endDate = endDate,
        title = title,
        description = description,
        slots = slots,
        isPrivate = isPrivate,
        price = price,
        host = host.mapToDto(),
        attendees = attendees.map { it.mapToDto() },
        address = address.mapToDto(),
        boardgames = boardgames.map { it.mapToDto() }
    )

    override fun mapToUI(): EventUI = EventUI(
        id = id,
        thumbnail = thumbnail.mapToUI(),
        startDate = startDate.toEpochMilliseconds(),
        endDate = endDate.toEpochMilliseconds(),
        title = title,
        description = description,
        slots = slots,
        isPrivate = isPrivate,
        price = price,
        host = host.mapToUI(),
        attendees = attendees.map { it.mapToUI() },
        address = address.mapToUI(),
        boardgames = boardgames.map { it.mapToUI() }
    )
}