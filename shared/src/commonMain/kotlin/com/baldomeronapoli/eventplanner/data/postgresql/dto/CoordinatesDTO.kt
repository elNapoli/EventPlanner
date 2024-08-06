package com.baldomeronapoli.eventplanner.data.postgresql.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoordinatesDTO(
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("longitude")
    val longitude: Double,
)