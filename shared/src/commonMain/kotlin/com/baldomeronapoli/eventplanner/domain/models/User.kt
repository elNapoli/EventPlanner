package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.UserDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.UserUI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: String,

    @SerialName("email")
    val email: String,

    @SerialName("name")
    val name: String?,

    @SerialName("picture")
    val picture: String?,

    ) : BiMappable<UserDTO, UserUI> {
    override fun mapToDto(): UserDTO = UserDTO(
        id = id,
        email = email,
        name = name,
        picture = picture,
    )

    override fun mapToUI(): UserUI = UserUI(
        id = id,
        email = email,
        name = name,
        picture = picture
    )
}