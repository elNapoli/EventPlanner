package com.baldomeronapoli.eventplanner.android.navigation.search

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class SearchRoute(override val path: String, override val title: String) :
    Route(path, title) {
    data object Index : SearchRoute("search-index", "Buscar")
}