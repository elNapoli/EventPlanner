package com.baldomeronapoli.eventplanner.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HitDto<T>(
    val hits: List<T>,
    val page: Int,
    val nbHits: Int,
    val nbPages: Int,
    val hitsPerPage: Int,
    val processingTimeMS: Int,
    val query: String
)