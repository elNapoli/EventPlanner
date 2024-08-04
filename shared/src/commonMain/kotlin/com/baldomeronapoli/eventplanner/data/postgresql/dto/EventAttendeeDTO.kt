package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventAttendeeDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("users")
    val user: UserDTO
) : Mappable<User> {
    override fun toInstance(): User = user.toInstance()
}
