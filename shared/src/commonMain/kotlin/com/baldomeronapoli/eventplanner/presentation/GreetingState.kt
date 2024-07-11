package com.baldomeronapoli.eventplanner.presentation

data class GreetingState(
    val data: String,
    val isLoading: Boolean,
    val errorMessage: String?
) {
    companion object {
        fun default() = GreetingState(
            data = "",
            isLoading = false,
            errorMessage = null
        )
    }
}