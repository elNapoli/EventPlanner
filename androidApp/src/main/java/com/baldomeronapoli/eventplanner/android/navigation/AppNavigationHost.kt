package com.baldomeronapoli.eventplanner.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.auth.authGraph
import com.baldomeronapoli.eventplanner.android.navigation.home.homeGraph
import com.baldomeronapoli.eventplanner.android.navigation.onboard.onboardGraph
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import org.koin.compose.koinInject

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    navigationViewModel: NavigationViewModel,
) {
    val sharedPrefs = koinInject<SharePreferences>()
    val startDestination = if (sharedPrefs.getShownOnboarding()) {
        MainRoute.Onboard.path
    } else {
        MainRoute.Auth.path
    }
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = MainRoute.Home.path
    ) {
        homeGraph(onNavigationEvent = navigationViewModel::onEvent)
        onboardGraph(onNavigationEvent = navigationViewModel::onEvent)
        authGraph(onNavigationEvent = navigationViewModel::onEvent)
    }
}