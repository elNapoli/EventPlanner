package com.baldomeronapoli.eventplanner.android.navigation.eventDetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.components.NTopBar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.eventDetail.EventDetailScreen


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.eventDetailGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = EventDetailRoute.Index.path,
        route = MainRoute.EventDetail.path
    ) {
        composable(EventDetailRoute.Index.path) {
            ScaffoldWithBottomBarNavigation(
                topBar = {
                    NTopBar(title = "Shawn Mendes The Virtual Tour 2021 indes The")
                }
            ) {

                EventDetailScreen()

            }
        }

    }
}
