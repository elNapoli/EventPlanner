package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.BoardGameDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.BoardGameUI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardGame(
    @SerialName("id") val id: Int,
    @SerialName("board_game_publisher") val boardGamePublisher: String?,
    @SerialName("image") var image: String?,
    @SerialName("name") var name: String?,
    @SerialName("thumbnail") var thumbnail: String?,
    @SerialName("year_published") var yearPublished: Int?,
    @SerialName("bgg_id") var bggId: Int?,
) : BiMappable<BoardGameDTO, BoardGameUI> {

    override fun mapToDto(): BoardGameDTO {
        return BoardGameDTO(
            id = id,
            boardGamePublisher = boardGamePublisher,
            image = image,
            name = name,
            bggId = bggId,
            thumbnail = thumbnail,
            yearPublished = yearPublished
        )
    }

    override fun mapToUI(): BoardGameUI {
        return BoardGameUI(
            id = id,
            boardGamePublisher = boardGamePublisher,
            image = image,
            name = name,
            bggId = bggId,
            thumbnail = thumbnail,
            yearPublished = yearPublished
        )
    }
}