package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.firebaseModels.FBoardGame
import com.baldomeronapoli.eventplanner.mappers.Mappable
import com.baldomeronapoli.eventplanner.utils.randomUUID
import kotlinx.serialization.Serializable

@Serializable
data class BoardGame(
    override val id: String = randomUUID,
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
) : BaseModel, Mappable<FBoardGame> {
    override fun map(): FBoardGame =
        FBoardGame(
            id = id.toInt(),
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