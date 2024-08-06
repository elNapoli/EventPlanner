package com.baldomeronapoli.eventplanner.android.navigation.home

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.LocalMainViewModel
import com.baldomeronapoli.eventplanner.android.components.NTopBar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.EventDetailRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.home.HomeScreen

fun NavGraphBuilder.homeGraph(onNavigationEvent: (NavigationEvent) -> Unit) {

    navigation(
        startDestination = HomeRoute.Index.path,
        route = MainRoute.Home.path
    ) {
        composable(HomeRoute.Index.path) {
            val mainViewModel = LocalMainViewModel.current
            val mainState = mainViewModel.uiState.collectAsStateWithLifecycle().value
            ScaffoldWithBottomBarNavigation(
                topBar = {
                    NTopBar(title = mainState.user?.email ?: "Hola")
                }
            ) {
                HomeScreen {
                    onNavigationEvent(NavigationEvent.OnNavigateToScreen(EventDetailRoute.Index))
                }
            }

        }

        composable(HomeRoute.Test.path) {
            Text("esta es otra pantalla")
        }
    }
}
