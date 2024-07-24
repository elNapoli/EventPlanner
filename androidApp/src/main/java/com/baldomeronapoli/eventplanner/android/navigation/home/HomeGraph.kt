package com.baldomeronapoli.eventplanner.android.navigation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.components.ProfilePicture
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
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
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)

                            ) {
                                ProfilePicture(
                                    letter = "A",
                                    modifier = Modifier
                                        .size(40.dp)
                                )
                                Text("Baldomero")
                            }
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
                    onNavigationEvent(NavigationEvent.OnNavigateToScreen(HomeRoute.Test))
                }
            }

        }

        composable(HomeRoute.Test.path) {
            Text("esta es otra pantalla")
        }
    }
}
