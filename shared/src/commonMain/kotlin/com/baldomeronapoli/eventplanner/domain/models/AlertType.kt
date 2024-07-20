package com.baldomeronapoli.eventplanner.domain.models

enum class AlertType {
    SUCCESS,
    ERROR,
    WARNING
}


data class ErrorDialog(
    val title: String,
    val message: String,
    val type: AlertType,
)