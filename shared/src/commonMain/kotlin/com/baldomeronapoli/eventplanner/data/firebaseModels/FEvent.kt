package com.baldomeronapoli.eventplanner.data.firebaseModels

import kotlinx.serialization.Serializable

@Serializable
data class FEvent(
    val id: String,
    val thumbnail: String,
    var title: String,
    var description: String,
    var hostId: String,
    var games: String,
    var slots: Int,
    var date: String,
    var isPrivate: Boolean = false,
    var price: Double = 0.0,
)