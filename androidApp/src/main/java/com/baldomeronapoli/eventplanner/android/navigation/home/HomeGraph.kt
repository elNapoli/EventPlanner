package com.baldomeronapoli.eventplanner.android.navigation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.components.OrganizerAvatar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.EventDetailRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = HomeRoute.Index.path,
        route = MainRoute.Home.path
    ) {
        composable(HomeRoute.Index.path) {

            ScaffoldWithBottomBarNavigation(
                topBar = {
                    TopAppBar(

                        title = {
                            OrganizerAvatar()
                        },
                        actions = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.Notifications,
                                    contentDescription = null
                                )
                            }
                        },

                        )

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
