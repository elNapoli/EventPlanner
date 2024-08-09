package com.baldomeronapoli.eventplanner.android.navigation.myevents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomero.napoli.eventplannerevents.presentation.EventViewModel
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NTopBar
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.eventDetail.EventDetailRoute
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.views.base.ScaffoldWithBottomBarNavigation
import com.baldomeronapoli.eventplanner.android.views.events.CreateEventScreen
import com.baldomeronapoli.eventplanner.android.views.events.EventsListScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.myEventGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = MyEventsRoute.Index.path,
        route = MainRoute.MyEvents.path
    ) {
        composable(MyEventsRoute.Index.path) {
            val viewmodel: EventViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
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
                EventsListScreen(
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
                    goToCreateEvent = {
                        onNavigationEvent(NavigationEvent.OnNavigateToScreen(MyEventsRoute.Create))
                    }
                )
            }
        }


        composable(MyEventsRoute.Create.path) {
            val viewmodel: EventViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            Scaffold(
                topBar = {
                    NTopBar(title = stringResource(id = R.string.create_event)) {
                        onNavigationEvent(NavigationEvent.OnBack)
                    }
                },

                ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        CreateEventScreen(
                            uiState = uiState.value,
                            effect = viewmodel.effect,
                            onIntent = viewmodel::sendIntent,
                            goBack = {
                                onNavigationEvent(NavigationEvent.OnBack)
                            }
                        )
                    }
                }
            }

        }

    }
}
