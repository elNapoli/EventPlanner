package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.UserDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.UserUI
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: String,

    @SerialName("create_at")
    val createdAt: Instant,

    @SerialName("email")
    val email: String,

    @SerialName("aud")
    val aud: String,

    @SerialName("email_confirmed_at")
    val emailConfirmedAt: Instant?,

    @SerialName("raw_user_meta_data")
    val rawUserMetaData: String?,
) : BiMappable<UserDTO, UserUI> {
    override fun mapToDto(): UserDTO = UserDTO(
        id = id,
        email = email,
        createdAt = createdAt,
        aud = aud,
        emailConfirmedAt = emailConfirmedAt,
        rawUserMetaData = rawUserMetaData
    )

    override fun mapToUI(): UserUI = UserUI(
        id = id,
        email = email,
        createdAt = createdAt,
        aud = aud,
        emailConfirmedAt = emailConfirmedAt,
        rawUserMetaData = rawUserMetaData
    )
}