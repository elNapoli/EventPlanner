package com.baldomeronapoli.eventplanner.domain.models

import io.github.jan.supabase.gotrue.user.UserInfo
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("aud")
    val aud: String,
    @SerialName("confirmation_sent_at")
    val confirmationSentAt: Instant? = null,
    @SerialName("confirmed_at")
    val confirmedAt: Instant? = null,
    @SerialName("created_at")
    val createdAt: Instant? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("email_confirmed_at")
    val emailConfirmedAt: Instant? = null,
    @SerialName("id")
    override val id: String,
    @SerialName("last_sign_in_at")
    val lastSignInAt: Instant? = null,
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("role")
    val role: String? = null,
    @SerialName("updated_at")
    val updatedAt: Instant? = null,
) : BaseModel


fun UserInfo.toUserUI(): User = User(
    aud = this.aud,
    confirmationSentAt = this.confirmationSentAt,
    confirmedAt = this.confirmedAt,
    createdAt = this.createdAt,
    email = this.email,
    emailConfirmedAt = this.emailConfirmedAt,
    id = this.id,
    lastSignInAt = this.lastSignInAt,
    phone = this.phone,
    role = this.role,
    updatedAt = this.updatedAt,
)
