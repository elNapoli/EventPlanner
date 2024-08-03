package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.mappers.Mappable
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO(
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
) : Mappable<User> {
    override fun map(): User = User(
        id = id,
        email = email,
        createdAt = createdAt,
        aud = aud,
        emailConfirmedAt = emailConfirmedAt,
        rawUserMetaData = rawUserMetaData
    )
}


fun UserInfo?.map(): User? {
    return this?.let {
        User(
            id = id,
            email = email!!,
            createdAt = createdAt!!,
            aud = aud,
            emailConfirmedAt = emailConfirmedAt,
            rawUserMetaData = null
        )
    }
}

