package com.baldomeronapoli.eventplanner.data.models.requetsDto

import kotlinx.serialization.Serializable

@Serializable
data class QueryRequestDto(val query: String)