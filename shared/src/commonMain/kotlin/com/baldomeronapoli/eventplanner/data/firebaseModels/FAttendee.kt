package com.baldomeronapoli.eventplanner.data.firebaseModels

import com.baldomeronapoli.eventplanner.domain.models.Attendee
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.Serializable

@Serializable
data class FAttendee(
    val id: String,
    val userName: String,
    val userEmail: String
) : Mappable<Attendee> {
    override fun map(): Attendee = Attendee(id, userEmail, userName)
}