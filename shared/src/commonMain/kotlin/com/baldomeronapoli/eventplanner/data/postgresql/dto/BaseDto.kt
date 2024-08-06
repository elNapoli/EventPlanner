package com.baldomeronapoli.eventplanner.data.postgresql.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseDto<T>(
    @SerialName("data")
    val data: T,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_records")
    val totalRecords: Int,
    @SerialName("records_per_page")
    val recordsPerPage: Int,
    @SerialName("current_page")
    val currentPage: Int
)

@Serializable
data class BaseRequest<T>(
    @SerialName("data")
    val data: T,
)

@Serializable
data class DbOperationResponse(
    @SerialName("id")
    val id: Int,
)

