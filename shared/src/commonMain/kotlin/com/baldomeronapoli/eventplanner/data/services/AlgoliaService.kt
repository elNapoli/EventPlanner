package com.baldomeronapoli.eventplanner.data.services

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.data.Constant
import com.baldomeronapoli.eventplanner.data.firebaseModels.FGames
import com.baldomeronapoli.eventplanner.data.models.requetsDto.QueryRequestDto
import com.baldomeronapoli.eventplanner.data.models.responsesDto.HitResponseDto
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AlgoliaService(private val httpClient: HttpClient) {

    @NativeCoroutines
    suspend fun searchBoardGames(query: String): NetworkResult<HitResponseDto<FGames>> {
        return try {
            val queryData = QueryRequestDto(query)
            val response =
                httpClient.post(Constant.ALGOLIA_URL) {
                    setBody(Json.encodeToString(queryData))
                }
            val json = Json { ignoreUnknownKeys = true }
            val data = response.bodyAsText()
            Logger.e(data)
            val hitsResponse: HitResponseDto<FGames> = json.decodeFromString(data)
            NetworkResult.Success(hitsResponse)
        } catch (e: Throwable) {
            NetworkResult.Error(exception = e, data = null)
        }
    }
}

