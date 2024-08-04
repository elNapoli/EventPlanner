package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.User
import com.baldomeronapoli.eventplanner.mappers.Mappable
import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive


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
) : Mappable<User>, JsonObjectBuilder {
    override fun toInstance(): User = User(
        id = id,
        email = email,
        createdAt = createdAt,
        aud = aud,
        emailConfirmedAt = emailConfirmedAt,
        rawUserMetaData = rawUserMetaData
    )

    override fun buildJsonObject(): JsonObject = kotlinx.serialization.json.buildJsonObject {
        put("thumbnail", JsonPrimitive(id))
        put("title", JsonPrimitive(email))
        put("description", JsonPrimitive(createdAt.toString()))
        put("host_id", JsonPrimitive(aud))
        put("slots", JsonPrimitive(emailConfirmedAt.toString()))
        put("is_private", JsonPrimitive(rawUserMetaData))
    }
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

