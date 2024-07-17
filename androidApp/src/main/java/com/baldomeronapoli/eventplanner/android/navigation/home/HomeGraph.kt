package com.baldomeronapoli.eventplanner.android.navigation.home

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.home.HomeScreen
import com.baldomeronapoli.eventplanner.presentation.GreetingViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = HomeRoute.Index.path,
        route = MainRoute.Home.path
    ) {
        composable(HomeRoute.Index.path) {
            val viewmodel: GreetingViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState.value,
                sideEffect = viewmodel.sideEffect,
                onAction = viewmodel::onAction,
            ) {
                onNavigationEvent(NavigationEvent.OnNavigateToScreen(HomeRoute.Test))

            }
        }

        composable(HomeRoute.Test.path) {
            Text("esta es otra pantalla")
        }
    }
}
