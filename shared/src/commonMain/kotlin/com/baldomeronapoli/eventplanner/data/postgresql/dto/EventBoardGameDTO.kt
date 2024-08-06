package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventBoardGameDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("board_games")
    val boardGame: BoardGameDTO
) : Mappable<BoardGame> {
    override fun toInstance(): BoardGame = boardGame.toInstance()
}
