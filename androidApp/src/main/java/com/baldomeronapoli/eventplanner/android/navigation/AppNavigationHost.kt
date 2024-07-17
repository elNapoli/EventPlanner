package com.baldomeronapoli.eventplanner.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.home.homeGraph
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    appState: AppState,
    navigationViewModel: NavigationViewModel,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = MainRoute.Home.path
    ) {
        homeGraph(onNavigationEvent = navigationViewModel::onEvent)
    }
}