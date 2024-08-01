package com.baldomeronapoli.eventplanner.domain.models

import com.baldomeronapoli.eventplanner.data.firebaseModels.FEvent
import com.baldomeronapoli.eventplanner.mappers.Mappable
import com.baldomeronapoli.eventplanner.utils.randomUUID
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.storage.File

data class Event(
    override val id: String = randomUUID,
    val thumbnail: File? = null,
    val thumbnailUrl: String = "",
    var title: String = "",
    var description: String = "",
    var boardgames: List<BoardGame> = emptyList(),
    var attendees: List<Attendee> = emptyList(),
    var host: Attendee = Attendee(),
    var slots: Int = 0,
    var date: Timestamp = Timestamp.now(),
    var isPrivate: Boolean = false,
    var price: Double = 0.0,
    var place: Address = Address(),
) : BaseModel, Mappable<FEvent> {

    fun isValid(): Boolean {

        return (this.thumbnail != null && this.title.isNotEmpty() && this.description.isNotEmpty()
                && this.boardgames.isNotEmpty()) && this.place.street != null && this.place.coordinates.latitude != 0.0 && this.place.coordinates.longitude != 0.0
    }

    override fun map(): FEvent = FEvent(
        id = id,
        thumbnail = thumbnailUrl,
        title = title,
        description = description,
        attendeesId = attendees.map { it.id },
        slots = slots,
        date = date,
        isPrivate = isPrivate,
        price = price,
        hostId = host.id
    )
}


val ho = Timestamp.now()

