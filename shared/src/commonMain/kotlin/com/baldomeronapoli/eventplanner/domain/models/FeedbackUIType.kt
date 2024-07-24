package com.baldomeronapoli.eventplanner.domain.models

enum class FeedbackUIType {
    SUCCESS,
    ERROR,
    WARNING
}


data class FeedbackUI(
    val title: String,
    val message: String,
    val type: FeedbackUIType,
    val show: Boolean
) {
    constructor() : this(title = "", message = "", type = FeedbackUIType.SUCCESS, show = false)
}