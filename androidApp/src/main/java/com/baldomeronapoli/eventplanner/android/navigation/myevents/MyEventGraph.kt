package com.baldomeronapoli.eventplanner.android.navigation.myevents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.events.MyEventsScreen

fun NavGraphBuilder.myEventGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = MyEventsRoute.Index.path,
        route = MainRoute.MyEvents.path
    ) {
        composable(MyEventsRoute.Index.path) {
            ScaffoldWithBottomBarNavigation(
                topBar = {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_events),
                            style = MaterialTheme.typography.headlineLarge,
                            color = GrayTitle
                        )
                    }
                }
            ) {
                MyEventsScreen()
            }
        }

    }
}