package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.ThumbnailDto
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.ThumbnailUI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("id")
    val id: String,
    @SerialName("bucket_id")
    val bucketId: String,
    @SerialName("name")
    val name: String,
) : BiMappable<ThumbnailDto, ThumbnailUI> {

    override fun mapToDto(): ThumbnailDto = ThumbnailDto(
        id = id,
        name = name,
        bucketId = bucketId
    )

    override fun mapToUI(): ThumbnailUI = ThumbnailUI(
        id = id,
        name = name,
        bucketId = bucketId
    )
}
