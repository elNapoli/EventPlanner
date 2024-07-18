package com.baldomeronapoli.eventplanner.domain.models

data class ValidationError(
    val property: String = "",
    val message: String = ""
)