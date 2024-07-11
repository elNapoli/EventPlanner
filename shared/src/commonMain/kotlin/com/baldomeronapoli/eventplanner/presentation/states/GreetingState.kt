package com.baldomeronapoli.eventplanner.presentation.states

data class GreetingState(
    val greet: String = "",
    val loading: Boolean = false,
    val error: String? = null
)