package com.baldomeronapoli.eventplanner.android.navigation.home

import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomero.napoli.eventplannerevents.presentation.EventViewModel
import com.baldomeronapoli.eventplanner.android.components.NTopBar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.EventDetailRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(onNavigationEvent: (NavigationEvent) -> Unit) {

    navigation(
        startDestination = HomeRoute.Index.path,
        route = MainRoute.Home.path
    ) {
        composable(HomeRoute.Index.path) {

            val viewmodel: EventViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            ScaffoldWithBottomBarNavigation(
                topBar = {
                    NTopBar(title = "asdfasdf")
                }
            ) {
                HomeScreen(
                    uiState = uiState.value,
                    effect = viewmodel.effect,
                    onIntent = viewmodel::sendIntent,
                    goToEventDetail = { event ->
                        onNavigationEvent(
                            NavigationEvent.NavigateToDetailScreen(
                                EventDetailRoute.Index,
                                event.id
                            )
                        )
                    },
                )
            }

        }

        composable(HomeRoute.Test.path) {
            Text("esta es otra pantalla")
        }
    }
}
