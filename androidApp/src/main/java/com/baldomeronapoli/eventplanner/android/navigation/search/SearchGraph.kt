package com.baldomeronapoli.eventplanner.android.navigation.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.search.SearchScreen
import com.baldomeronapoli.eventplanner.android.views.search.TopAppBarSearchScreen


@OptIn(ExperimentalMaterial3Api::class)
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
                SearchScreen()
            }
        }

    }
}
