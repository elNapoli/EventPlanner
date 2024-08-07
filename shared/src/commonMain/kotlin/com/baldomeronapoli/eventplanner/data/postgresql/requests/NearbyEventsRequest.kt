package com.baldomeronapoli.eventplanner.data.postgresql.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NearbyEventsRequest(
    @SerialName("records_per_page")
    val recordsPerPage: Int,
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,


    )