package com.baldomeronapoli.eventplanner.android.navigation.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.EventDetailRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.search.SearchScreen
import com.baldomeronapoli.eventplanner.android.views.search.TopAppBarSearchScreen


fun NavGraphBuilder.searchGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = SearchRoute.Index.path,
        route = MainRoute.Search.path
    ) {
        composable(SearchRoute.Index.path) {
            ScaffoldWithBottomBarNavigation(
                topBar = { TopAppBarSearchScreen() }
            ) {
                SearchScreen {
                    onNavigationEvent(NavigationEvent.OnNavigateToScreen(EventDetailRoute.Index))
                }
            }
        }

    }
}
