package com.baldomeronapoli.eventplanner.data.postgresql.dto

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardGameDTO(
    @SerialName("id") val id: Int,
    @SerialName("board_game_publisher") val boardGamePublisher: String?,
    @SerialName("description") var description: String?,
    @SerialName("image") var image: String?,
    @SerialName("max_players") var maxPlayers: String?,
    @SerialName("max_playtime") var maxPlaytime: String?,
    @SerialName("min_players") var minPlayers: String?,
    @SerialName("categories") val categories: List<String>,
    @SerialName("min_playtime") var minPlaytime: String?,
    @SerialName("name") var name: String?,
    @SerialName("playing_time") var playingTime: String?,
    @SerialName("thumbnail") var thumbnail: String?,
    @SerialName("year_published") var yearPublished: String?
) : Mappable<BoardGame> {
    override fun map(): BoardGame = BoardGame(
        id = id,
        boardGamePublisher = boardGamePublisher,
        description = description,
        image = image,
        maxPlayers = maxPlayers,
        maxPlaytime = maxPlaytime,
        minPlayers = minPlayers,
        categories = categories,
        minPlaytime = minPlaytime,
        name = name,
        playingTime = playingTime,
        thumbnail = thumbnail,
        yearPublished = yearPublished
    )
}
