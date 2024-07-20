package com.baldomeronapoli.eventplanner.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.auth.authGraph
import com.baldomeronapoli.eventplanner.android.navigation.home.homeGraph
import com.baldomeronapoli.eventplanner.android.navigation.onboard.onboardGraph
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.constants.Preferences
import com.baldomeronapoli.eventplanner.utils.sharedPrefs

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    navigationViewModel: NavigationViewModel,
) {
    val startDestination = if (sharedPrefs.getBoolean(Preferences.SHOW_ONBOARDING, true)) {
        MainRoute.Onboard.path
    } else {
        MainRoute.Auth.path
    }
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = startDestination
    ) {
        homeGraph(onNavigationEvent = navigationViewModel::onEvent)
        onboardGraph(onNavigationEvent = navigationViewModel::onEvent)
        authGraph(onNavigationEvent = navigationViewModel::onEvent)
    }
}