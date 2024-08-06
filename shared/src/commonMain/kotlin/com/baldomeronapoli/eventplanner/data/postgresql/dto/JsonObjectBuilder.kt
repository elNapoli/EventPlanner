package com.baldomeronapoli.eventplanner.data.postgresql.dto

import kotlinx.serialization.json.JsonObject

interface JsonObjectBuilder {
    fun buildJsonObject(): JsonObject
}