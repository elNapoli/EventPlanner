package com.baldomeronapoli.eventplanner.data.services

import com.baldomeronapoli.eventplanner.data.Constant
import com.baldomeronapoli.eventplanner.data.firebaseModels.FBoardGame
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
    suspend fun searchBoardGames(query: String): NetworkResult<HitResponseDto<FBoardGame>> {
        return try {
            val queryData = QueryRequestDto(query)
            val response =
                httpClient.post(Constant.ALGOLIA_URL) {
                    setBody(Json.encodeToString(queryData))
                }
            val json = Json { ignoreUnknownKeys = true }
            val data = response.bodyAsText()
            val hitsResponse: HitResponseDto<FBoardGame> = json.decodeFromString(data)
            NetworkResult.Success(hitsResponse)
        } catch (e: Throwable) {
            NetworkResult.Error(exception = e, data = null)
        }
    }
}

