package com.baldomeronapoli.eventplanner.android.navigation.myevents

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class MyEventsRoute(override val path: String, override val title: String) :
    Route(path, title) {
    data object Index : MyEventsRoute("my-events-index", "Buscar")
    data object Create : MyEventsRoute("create-event", "Iniciar sesion")

}