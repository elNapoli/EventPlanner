package com.baldomeronapoli.eventplanner.android.navigation.eventDetail

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class EventDetailRoute(override val path: String, override val title: String) :
    Route(path, title) {
    data object Index : EventDetailRoute("event-detail-route", "Iniciar sesion")
}