package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.postgresql.dto.BoardGameDTO
import com.baldomeronapoli.eventplanner.mappers.BiMappable
import com.baldomeronapoli.eventplanner.presentation.models.BoardGameUI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardGame(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("board_game_publisher")
    val boardGamePublisher: String? = null,

    @SerialName("description")
    var description: String? = null,

    @SerialName("image")
    var image: String? = null,

    @SerialName("max_players")
    var maxPlayers: String? = null,

    @SerialName("max_playtime")
    var maxPlaytime: String? = null,

    @SerialName("min_players")
    var minPlayers: String? = null,

    @SerialName("categories")
    val categories: List<String> = emptyList(),

    @SerialName("min_playtime")
    var minPlaytime: String? = null,

    @SerialName("name")
    var name: String? = null,

    @SerialName("playing_time")
    var playingTime: String? = null,

    @SerialName("thumbnail")
    var thumbnail: String? = null,

    @SerialName("year_published")
    var yearPublished: String? = null
) : BiMappable<BoardGameDTO, BoardGameUI> {

    override fun mapToDto(): BoardGameDTO {
        return BoardGameDTO(
            id = this.id,
            boardGamePublisher = this.boardGamePublisher,
            description = this.description,
            image = this.image,
            maxPlayers = this.maxPlayers,
            maxPlaytime = this.maxPlaytime,
            minPlayers = this.minPlayers,
            categories = this.categories,
            minPlaytime = this.minPlaytime,
            name = this.name,
            playingTime = this.playingTime,
            thumbnail = this.thumbnail,
            yearPublished = this.yearPublished
        )
    }

    override fun mapToUI(): BoardGameUI {
        return BoardGameUI(
            id = this.id,
            boardGamePublisher = this.boardGamePublisher,
            description = this.description,
            image = this.image,
            maxPlayers = this.maxPlayers,
            maxPlaytime = this.maxPlaytime,
            minPlayers = this.minPlayers,
            categories = this.categories,
            minPlaytime = this.minPlaytime,
            name = this.name,
            playingTime = this.playingTime,
            thumbnail = this.thumbnail,
            yearPublished = this.yearPublished
        )
    }
}