package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.mvi.KmmEvent

sealed class GreetingEvent : KmmEvent {
    data object LoadData : GreetingEvent()
    data object OtroEvento : GreetingEvent()
}