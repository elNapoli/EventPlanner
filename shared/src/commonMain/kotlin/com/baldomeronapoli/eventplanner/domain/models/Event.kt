package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.utils.randomUUID
import dev.gitlive.firebase.storage.File

data class Event(
    val id: String = randomUUID,
    val thumbnail: File? = null,
    var title: String = "",
    var description: String = "",
    var games: String = "",
    var slots: Int? = null,
    var date: String = "",
    var isPrivate: Boolean = false,
    var price: Double = 0.0,
    val place: NPlace = NPlace()
) {

    fun isValid(): Boolean {

        return this.thumbnail != null && this.title.isNotEmpty() && this.description.isNotEmpty()
                && this.games.isNotEmpty() && this.slots != null && this.date.isNotEmpty()
                && this.place.street != null && this.place.coordinates.latitude != 0.0
                && this.place.coordinates.longitude != 0.0
    }

}