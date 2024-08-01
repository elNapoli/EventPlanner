package com.baldomeronapoli.eventplanner.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class NCoordinates(
    val latitude: Double,
    val longitude: Double,
)