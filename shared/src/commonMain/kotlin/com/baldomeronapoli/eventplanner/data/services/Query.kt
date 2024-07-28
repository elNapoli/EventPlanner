package com.baldomeronapoli.eventplanner.data.services

import com.baldomeronapoli.eventplanner.data.dto.HitDto
import com.baldomeronapoli.eventplanner.data.firebaseModels.FGames
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Query(val query: String)

class AlgoliaService(private val httpClient: HttpClient) {

    @NativeCoroutines
    suspend fun searchBoardGames(query: String): NetworkResult<HitDto<FGames>> {
        return try {
            val queryData = Query(query)
            val response =
                httpClient.post("https://ALGOLIA_APPLICATION_ID-dsn.algolia.net/1/indexes/name/query") {
                    setBody(Json.encodeToString(queryData))
                }
            val json = Json { ignoreUnknownKeys = true }
            val hitsResponse: HitDto<FGames> = json.decodeFromString(response.bodyAsText())
            NetworkResult.Success(hitsResponse)
        } catch (e: Throwable) {
            NetworkResult.Error(exception = e, data = null)
        }
    }
}

