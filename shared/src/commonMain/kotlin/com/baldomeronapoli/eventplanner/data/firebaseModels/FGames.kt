package com.baldomeronapoli.eventplanner.data.firebaseModels

import kotlinx.serialization.Serializable

@Serializable
data class FGames(
    val id: Int?,
    val boardgamepublisher: String?,
    var description: String?,
    var image: String?,
    var maxplayers: String?,
    var maxplaytime: String?,
    var minplayers: String?,
    val categorias: List<String> = emptyList(),
    var minplaytime: String?,
    var name: String?,
    var playingtime: String?,
    var thumbnail: String?,
    var yearpublished: String?,
)