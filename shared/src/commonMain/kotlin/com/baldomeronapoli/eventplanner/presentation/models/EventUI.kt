package com.baldomeronapoli.eventplanner.presentation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EventUI(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("thumbnail")
    var thumbnail: String = "",
    @SerialName("start_date")
    var startDate: String = "",
    @SerialName("end_date")
    var endDate: String = "",
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
)