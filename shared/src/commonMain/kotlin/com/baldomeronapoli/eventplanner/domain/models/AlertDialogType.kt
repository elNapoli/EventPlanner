package com.baldomeronapoli.eventplanner.domain.models

enum class AlertDialogType {
    SUCCESS,
    ERROR,
    WARNING
}


data class ErrorDialog(
    val title: String,
    val message: String,
    val type: AlertDialogType,
    val show: Boolean

)