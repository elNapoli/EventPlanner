package com.baldomeronapoli.eventplanner.android.navigation

import androidx.navigation.NavHostController
import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed interface NavigationEvent {

    data class OnSetContent(
        val activityNavController: NavHostController,
        val onBackPressed: () -> Unit
    ) : NavigationEvent

    data object OnBack : NavigationEvent

    data class OnNavigateToScreen(val route: Route) : NavigationEvent
}