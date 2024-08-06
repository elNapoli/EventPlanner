package com.baldomeronapoli.eventplanner.presentation.models

import com.baldomeronapoli.eventplanner.domain.models.Thumbnail
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThumbnailUI(
    @SerialName("id")
    val id: String = "",
    @SerialName("bucket_id")
    val bucketId: String = "events",
    @SerialName("name")
    val name: String = "",
) : Mappable<Thumbnail> {
    override fun toInstance(): Thumbnail = Thumbnail(
        id = id,
        name = name,
        bucketId = bucketId
    )
}
