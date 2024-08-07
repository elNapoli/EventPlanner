package com.baldomeronapoli.eventplanner.android.views.events

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.LocalMainViewModel
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.Gray40
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.theme.Orange
import com.baldomeronapoli.eventplanner.android.theme.White
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.baldomeronapoli.eventplanner.presentation.main.MainContract
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun EventsListScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    goToCreateEvent: () -> Unit,
    goToEventDetail: (EventUI) -> Unit

) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val mainViewModel = LocalMainViewModel.current
    val tabs = listOf("Próximos", "Pasados", "Organizados")
    LaunchedEffect(Unit) {
        onIntent(UiIntent.LoadAllEventsByCurrentId)
    }
    Scaffold(
        floatingActionButton = {
            if (tabIndex == 2) { // Mostrar el botón flotante solo en la pestaña "Organizados"
                FloatingActionButton(
                    onClick = goToCreateEvent,
                    containerColor = Orange // Color del botón flotante
                ) {
                    // Icono del botón flotante
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = Gray40,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[tabIndex])
                            .padding(5.dp)
                            .background(
                                color = White.copy(alpha = .3F),
                                shape = RoundedCornerShape(4.dp) // Forma del indicador
                            )
                            .fillMaxSize()
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleMedium,
                                color = if (tabIndex == index) Blue else GrayTitle, // Color del texto
                                fontWeight = if (tabIndex == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                    )
                }
            }
            Column(Modifier.padding(16.dp)) {
                if (uiState.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        CircularProgressIndicator()
                    }
                } else {
                    when (tabIndex) {
                        0 -> EventTab(
                            events = uiState.nextEvents,
                            goToEventDetail = { event ->
                                mainViewModel.sendIntent(MainContract.UiIntent.SetCurrentEvent(event))
                                goToEventDetail(event)
                            }
                        )

                        1 -> EventTab(
                            events = uiState.expiredEvents,
                            goToEventDetail = goToEventDetail
                        )

                        2 -> EventTab(
                            events = uiState.ownEvents,
                            goToEventDetail = { event ->
                                mainViewModel.sendIntent(MainContract.UiIntent.SetCurrentEvent(event))
                                goToEventDetail(event)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewMyEventsScreenLight(modifier: Modifier = Modifier) {
    val effect: StateFlow<Effect?> = MutableStateFlow(null)
    NPreview {
        EventsListScreen(
            uiState = UiState(),
            onIntent = {},
            effect = effect,
            goToCreateEvent = {},
            goToEventDetail = {}
        )
    }
}