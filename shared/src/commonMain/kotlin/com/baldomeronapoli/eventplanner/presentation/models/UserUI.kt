package com.baldomeronapoli.eventplanner.presentation.models

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUI(
    @SerialName("id")
    val id: String = "",
    @SerialName("email")
    val email: String = ""
) : Mappable<User> {
    override fun map(): User = User(id = id, email = email)
}