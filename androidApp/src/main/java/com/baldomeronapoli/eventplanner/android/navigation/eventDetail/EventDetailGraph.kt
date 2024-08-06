package com.baldomeronapoli.eventplanner.android.navigation.eventDetail

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.LocalMainViewModel
import com.baldomeronapoli.eventplanner.android.components.NTopBar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithoutBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.eventDetail.EventDetailScreen
import com.baldomeronapoli.eventplanner.presentation.event.EventViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.eventDetailGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = EventDetailRoute.Index.path,
        route = MainRoute.EventDetail.path
    ) {
        composable(EventDetailRoute.Index.createPath()) { backStackEntry ->
            val viewmodel: EventViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            val eventId = backStackEntry.arguments?.getString("eventId")

            val mainViewModel = LocalMainViewModel.current
            val mainState = mainViewModel.uiState.collectAsStateWithLifecycle().value
            ScaffoldWithoutBottomBarNavigation(
                topBar = {
                    NTopBar(
                        title = mainState.currentEvent!!.title,
                        onNavigationIcon = { onNavigationEvent(NavigationEvent.OnBack) })
                }
            ) {

                EventDetailScreen(
                    mainState = mainState,
                    effect = viewmodel.effect,
                    onIntent = viewmodel::sendIntent,
                    eventId = eventId!!
                )

            }
        }

    }
}
