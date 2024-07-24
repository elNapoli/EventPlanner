package com.baldomeronapoli.eventplanner.android.navigation.eventDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
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
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { onNavigationEvent(NavigationEvent.OnBack) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)

                            ) {
                                Text(
                                    text = "Shawn Mendes The Virtual Tour 2021 indes The",
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.SemiBold,
                                    color = GrayTitle

                                )
                            }
                        },


                        )

                }
            ) {

                EventDetailScreen()

            }
        }

    }
}
