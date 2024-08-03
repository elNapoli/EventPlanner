package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventAttendeeDTO
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventBoardGameDTO
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import kotlinx.serialization.SerialName

data class Event(
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
    @SerialName("slots")
    var slots: Int,
    @SerialName("is_private")
    var isPrivate: Boolean,
    @SerialName("price")
    var price: Double,
    @SerialName("users")
    var host: User,
    @SerialName("events_attendees")
    var attendees: List<User>,
    @SerialName("addresses")
    var address: Address,
    @SerialName("event_boardgames")
    var boardgames: List<BoardGame>
) : BiMappable<EventDTO, EventUI> {

    override fun mapToDto(): EventDTO {
        return EventDTO(
            id = this.id,
            thumbnail = this.thumbnail,
            startDate = this.startDate,
            endDate = this.endDate,
            title = this.title,
            description = this.description,
            hostId = this.host.id,
            slots = this.slots,
            isPrivate = this.isPrivate,
            price = this.price,
            host = this.host.mapToDto(),
            attendees = this.attendees.map { EventAttendeeDTO(id = 1, it.mapToDto()) },
            address = this.address.mapToDto(), // Assuming Address has a mapToDto() method
            boardgames = this.boardgames.map {
                EventBoardGameDTO(
                    id = 1,
                    it.mapToDto()
                )
            } // Assuming BoardGame has a mapToDto() method
        )
    }

    override fun mapToUI(): EventUI {
        return EventUI(
            id = this.id,
            thumbnail = this.thumbnail,
            startDate = this.startDate,
            endDate = this.endDate,
            title = this.title,
            description = this.description,
            slots = this.slots,
            isPrivate = this.isPrivate,
            price = this.price,
            host = this.host.mapToUI(),
            attendees = this.attendees.map { it.mapToUI() },
            address = this.address.mapToUI(),
            boardgames = this.boardgames.map { it.mapToUI() }
        )
    }
}