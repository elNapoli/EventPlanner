package com.baldomeronapoli.eventplanner.data.algolia

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json

actual fun nHttpClient(): HttpClient {
    return HttpClient(Darwin) {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json)
                append("X-Algolia-API-Key", "")
                append("X-Algolia-Application-ID", "ALGOLIA_APPLICATION_ID")
            }
        }
    }
}