package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.mvi.KmmState

data class GreetingState(
    val data: String,
    val isLoading: Boolean,
    val errorMessage: String?
) : KmmState {
    companion object {
        fun default() = GreetingState(
            data = "",
            isLoading = false,
            errorMessage = null
        )
    }
}