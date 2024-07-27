package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.data.firebaseModels.FEvent
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.randomUUID
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

class EventRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth
) : EventRepository {
    private fun generateUniqueFileName(): String {
        val timestamp = Clock.System.now().toEpochMilliseconds()
        return "thumbnail_${timestamp}_$randomUUID.jpg"
    }

    override suspend fun createEvent(
        id: String,
        name: String,
        date: String,
        thumbnail: File,
        description: String,
        games: String,
        slots: Int,
        street: String,
        lat: Double,
        lon: Double
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))
        try {

            val fileRef =
                storage.reference.child("${auth.currentUser!!.uid}/$id/${generateUniqueFileName()}")
            fileRef.putFile(thumbnail)
            val event = FEvent(
                id = id,
                thumbnail = fileRef.path,
                title = name,
                description = description,
                games = games,
                date = date,
                slots = slots,
                price = 0.0,
                isPrivate = false,
                hostId = auth.currentUser!!.uid
            )

            firestore.collection("events").document(id)
                .set(FEvent.serializer(), event) { encodeDefaults = true }
            emit(NetworkResult.Success(true))
        } catch (e: Throwable) {
            emit(NetworkResult.Error(exception = e, data = null))
        }

    }
}