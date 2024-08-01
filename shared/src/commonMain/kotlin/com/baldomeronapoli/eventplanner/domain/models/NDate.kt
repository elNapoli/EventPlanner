package com.baldomeronapoli.eventplanner.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class NDate(
    val date: String = "",
    val time: String = "",
) {
    override fun toString() = "$date $time"
}