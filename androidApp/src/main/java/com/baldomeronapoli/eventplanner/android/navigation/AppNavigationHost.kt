package com.baldomeronapoli.eventplanner.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.baldomeronapoli.eventplanner.android.navigation.auth.authGraph
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.eventDetailGraph
import com.baldomeronapoli.eventplanner.android.navigation.home.homeGraph
import com.baldomeronapoli.eventplanner.android.navigation.myevents.myEventGraph
import com.baldomeronapoli.eventplanner.android.navigation.onboard.onboardGraph
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.navigation.search.searchGraph
import com.baldomeronapoli.eventplanner.utils.SharePreferences
import org.koin.compose.koinInject

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationViewModel: NavigationViewModel,
) {
    val sharedPrefs = koinInject<SharePreferences>()
    val startDestination = if (sharedPrefs.getShownOnboarding()) {
        MainRoute.Onboard.path
    } else {
        sharedPrefs.getInitialRoute()
    }
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = startDestination
    ) {

        homeGraph(onNavigationEvent = navigationViewModel::onEvent)
        onboardGraph(onNavigationEvent = navigationViewModel::onEvent)
        authGraph(onNavigationEvent = navigationViewModel::onEvent)
        searchGraph(onNavigationEvent = navigationViewModel::onEvent)
        myEventGraph(onNavigationEvent = navigationViewModel::onEvent)
        eventDetailGraph(onNavigationEvent = navigationViewModel::onEvent)

    }
}