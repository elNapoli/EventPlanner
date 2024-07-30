package com.baldomeronapoli.eventplanner.data.firebaseModels

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.mappers.Mappable
import kotlinx.serialization.Serializable

@Serializable
data class FBoardGame(
    val id: Int,
    val boardgamepublisher: String?,
    var description: String?,
    var image: String?,
    var maxplayers: String?,
    var maxplaytime: String?,
    var minplayers: String?,
    val categorias: List<String>,
    var minplaytime: String?,
    var name: String?,
    var playingtime: String?,
    var thumbnail: String?,
    var yearpublished: String?,
) : Mappable<BoardGame> {
    override fun map(): BoardGame =
        BoardGame(
            id = id.toString(),
            boardgamepublisher = boardgamepublisher,
            description = description,
            image = image,
            maxplayers = maxplayers,
            maxplaytime = maxplaytime,
            minplayers = minplayers,
            categorias = categorias,
            minplaytime = minplaytime,
            name = name,
            playingtime = playingtime,
            thumbnail = thumbnail,
            yearpublished = yearpublished
        )
}