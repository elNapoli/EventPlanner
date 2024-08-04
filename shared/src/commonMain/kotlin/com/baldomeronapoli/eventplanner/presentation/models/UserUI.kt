package com.baldomeronapoli.eventplanner.presentation.models

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUI(
    @SerialName("id")
    val id: String = "",

    @SerialName("create_at")
    val createdAt: Instant = Clock.System.now(),

    @SerialName("email")
    val email: String = "",

    @SerialName("aud")
    val aud: String = "",

    @SerialName("email_confirmed_at")
    val emailConfirmedAt: Instant? = Clock.System.now(),

    @SerialName("raw_user_meta_data")
    val rawUserMetaData: String? = "",
) : Mappable<User> {
    override fun toInstance(): User = User(
        id = id,
        email = email,
        createdAt = createdAt,
        aud = aud,
        emailConfirmedAt = emailConfirmedAt,
        rawUserMetaData = rawUserMetaData
    )
}