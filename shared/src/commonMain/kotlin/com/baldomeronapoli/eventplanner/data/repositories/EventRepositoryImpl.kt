package com.baldomeronapoli.eventplanner.data.repositories

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.data.dto.HitDto
import com.baldomeronapoli.eventplanner.data.firebaseModels.FEvent
import com.baldomeronapoli.eventplanner.data.firebaseModels.FGames
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.baldomeronapoli.eventplanner.utils.randomUUID
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.storage.File
import dev.gitlive.firebase.storage.FirebaseStorage
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Query(val query: String)
class EventRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth,
    private val httpClient: HttpClient
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

    override suspend fun searchBoardGames(query: String): Flow<NetworkResult<List<BoardGame>>> =
        flow {
            val queryData = Query(query)
            val a =
                httpClient.post("https://ALGOLIA_APPLICATION_ID-dsn.algolia.net/1/indexes/name/query") {
                    setBody(Json.encodeToString(queryData))
                }
            val json = Json {
                ignoreUnknownKeys = true
            }
            val hitsResponse: HitDto<FGames> = json.decodeFromString(a.bodyAsText())
            Logger.d(hitsResponse.toString())
            emit(
                NetworkResult.Success(
                    hitsResponse.hits.map {
                        BoardGame(
                            id = it.id.toString(),
                            image = it.image.toString(),
                            name = "${it.name} (${it.yearpublished})",
                            thumbnail = it.thumbnail.toString()
                        )
                    }
                )
            )
        }
}