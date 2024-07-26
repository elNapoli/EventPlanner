package com.baldomeronapoli.eventplanner.domain.models

data class Event(
    val id: String,
    val hostId: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val isPaid: Boolean,
    val price: Double,
)