package com.baldomeronapoli.eventplanner.android.navigation.home

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class HomeRoute(override val path: String, override val title: String) : Route(path, title) {
    data object Index : HomeRoute("home-index", "Home Index")
    data object Test : HomeRoute("home-test", "Home Test")
}