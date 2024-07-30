package com.baldomeronapoli.eventplanner.data.managers

import com.baldomeronapoli.eventplanner.data.collections.EventsCollection
import com.baldomeronapoli.eventplanner.data.firebaseModels.FAddress
import com.baldomeronapoli.eventplanner.data.firebaseModels.FAttendee
import com.baldomeronapoli.eventplanner.data.firebaseModels.FBoardGame
import com.baldomeronapoli.eventplanner.data.firebaseModels.FEvent
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.randomUUID
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.FirebaseStorage
import kotlinx.datetime.Clock

class EventManager(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth,
) {
    private val userId = auth.currentUser!!.uid
    private fun generateUniqueFileName(): String {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        return "thumbnail_${timestamp}_$randomUUID.jpg"
    }

    private suspend fun uploadThumbnail(thumbnail: File, eventId: String): String? {
        try {
            val fileRef =
                storage.reference.child("${userId}/$eventId/${generateUniqueFileName()}")
            fileRef.putFile(thumbnail)
            return fileRef.getDownloadUrl()
        } catch (e: Throwable) {
            return null
        }
    }

    private suspend fun createEvent(event: FEvent): String? {
        try {
            firestore.collection(EventsCollection.COLLECTION_NAME).document(event.id)
                .set(FEvent.serializer(), event) { encodeDefaults = true }
            return event.id
        } catch (e: Throwable) {
            return null
        }

    }

    private suspend fun addAttendeeToEvent(attendees: List<FAttendee>, eventId: String): Boolean {

        try {
            for (a in attendees) {
                firestore.collection(EventsCollection.COLLECTION_NAME).document(eventId)
                    .collection(EventsCollection.EventSubCollection.ATTENDEES)
                    .document(a.id)
                    .set(
                        FAttendee.serializer(), a
                    ) { encodeDefaults = true }
            }
            return true
        } catch (e: Throwable) {
            return false
        }
    }

    private suspend fun setEventAddress(address: FAddress, eventId: String): Boolean {
        try {

            firestore.collection(EventsCollection.COLLECTION_NAME).document(eventId)
                .collection(EventsCollection.EventSubCollection.ADDRESSES)
                .document(address.id)
                .set(FAddress.serializer(), address) { encodeDefaults = true }
            return true
        } catch (e: Throwable) {
            return false
        }

    }

    private suspend fun addGamesToEvent(boardGame: List<FBoardGame>, eventId: String): Boolean {
        try {
            for (game in boardGame) {
                firestore.collection(EventsCollection.COLLECTION_NAME).document(eventId)
                    .collection(EventsCollection.EventSubCollection.BOARDGAMES)
                    .document(game.id.toString())
                    .set(
                        FBoardGame.serializer(), game
                    ) { encodeDefaults = true }
            }
            return true
        } catch (e: Throwable) {
            return false
        }

    }

    @NativeCoroutines
    suspend fun createEvent(
        thumbnail: File,
        event: FEvent,
        games: List<FBoardGame>,
        address: FAddress
    ): NetworkResult<Boolean> {
        try {
            val fAttendees = FAttendee(
                id = userId,
                userName = auth.currentUser!!.displayName!!,
                userEmail = auth.currentUser!!.email!!
            )
            event.attendeesId = listOf(fAttendees.id)
            event.hostId = fAttendees.id

            val path = uploadThumbnail(thumbnail, eventId = event.id)
                ?: return NetworkResult.Error(
                    exception = Throwable("no se pudo subir el archivos"),
                    data = null
                )
            event.thumbnail = path
            createEvent(event)
                ?: return NetworkResult.Error(
                    exception = Throwable("no se pudo crear el evento"),
                    data = null
                )
            if (!addAttendeeToEvent(listOf(fAttendees), eventId = event.id)) {
                return NetworkResult.Error(
                    exception = Throwable("no se pudo asociar el usuario al evento"),
                    data = null
                )
            }

            if (!addGamesToEvent(games, eventId = event.id)) {
                return NetworkResult.Error(
                    exception = Throwable("no se pudo asociar juegos al evento"),
                    data = null
                )
            }
            if (!setEventAddress(address, eventId = event.id)) {
                return NetworkResult.Error(
                    exception = Throwable("no se pudo guardar la dirección del evento"),
                    data = null
                )
            }


            return NetworkResult.Success(true)
        } catch (e: Throwable) {
            return NetworkResult.Error(exception = e, data = null)
        }

    }


    @NativeCoroutines
    suspend fun getEventsByAttendee(): NetworkResult<List<Event>> {
        try {
            val events: MutableList<Event> = mutableListOf()
            var fEvent: FEvent
            var eventTemp: Event
            firestore.collection(EventsCollection.COLLECTION_NAME)
                .where { EventsCollection.EventFields.ATTENDEES_ID contains userId }
                .get().documents.map { event ->
                    fEvent = event.data(FEvent.serializer())
                    val boardGames: List<BoardGame> =
                        event.reference.collection(EventsCollection.EventSubCollection.BOARDGAMES)
                            .get().documents.map { game ->
                                game.data(
                                    FBoardGame.serializer()
                                ).map()
                            }
                    val attendees =
                        event.reference.collection(EventsCollection.EventSubCollection.ATTENDEES)
                            .get().documents.map { game ->
                                game.data(
                                    FAttendee.serializer()
                                ).map()
                            }

                    val address =
                        event.reference.collection(EventsCollection.EventSubCollection.ADDRESSES)
                            .get().documents.first().data(FAddress.serializer())
                    eventTemp = fEvent.map()
                    eventTemp.attendees = attendees
                    eventTemp.host = attendees.first { it.id == userId }
                    eventTemp.boardgames = boardGames
                    eventTemp.place = address.map()
                    events.add(eventTemp)
                }

            return NetworkResult.Success(events)
        } catch (e: Throwable) {
            return NetworkResult.Error(exception = e, data = emptyList())
        }
    }
}
