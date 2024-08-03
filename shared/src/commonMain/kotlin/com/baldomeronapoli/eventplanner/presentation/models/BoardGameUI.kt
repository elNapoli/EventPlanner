package com.baldomeronapoli.eventplanner.presentation.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardGameUI(
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
)