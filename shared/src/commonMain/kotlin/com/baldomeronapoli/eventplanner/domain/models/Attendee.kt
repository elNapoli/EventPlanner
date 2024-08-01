package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.firebaseModels.FAttendee
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.Serializable

@Serializable
data class Attendee(
    override val id: String = "",
    val userName: String = "",
    val userEmail: String = ""
) : BaseModel, Mappable<FAttendee> {
    override fun map(): FAttendee = FAttendee(
        id = id,
        userName = userName,
        userEmail = userEmail
    )
}