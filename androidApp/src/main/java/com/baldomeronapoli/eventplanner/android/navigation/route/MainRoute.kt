package com.baldomeronapoli.eventplanner.android.navigation.route

open class Route(open val path: String, open val title: String)


sealed class MainRoute(override val path: String, override val title: String) : Route(path, title) {
    data object Home : MainRoute("home", "Home")
    data object Search : MainRoute("search", "Search")
}