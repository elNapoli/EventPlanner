package com.baldomeronapoli.eventplanner.android.navigation.route

import com.baldomeronapoli.eventplanner.data.utils.InitialRoute

open class Route(open val path: String, open val title: String)


sealed class MainRoute(override val path: String, override val title: String) : Route(path, title) {
    data object Home : MainRoute(InitialRoute.HOME, "Home")
    data object Search : MainRoute("search", "Search")
    data object MyEvents : MainRoute("my-event", "Mis eventos")
    data object Profile : MainRoute("profile", "Perfil")
    data object Onboard : MainRoute("onboard", "Onboarding")
    data object Auth : MainRoute(InitialRoute.AUTH, "Auth")
    data object EventDetail : MainRoute("event-detail", "Auth")
}