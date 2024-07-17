package com.baldomeronapoli.eventplanner.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.AppNavigationHost
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.NavigationViewModel
import com.baldomeronapoli.eventplanner.android.theme.MyTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, appState: AppState) {
    val navigationViewModel = koinViewModel<NavigationViewModel>()
    navigationViewModel.onEvent(
        NavigationEvent.OnSetContent(
            activityNavController = appState.navController,
        ) {
            // no tengo idea que colocar aca
        })
    MyTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                TopAppBar(
                    title = { Text(appState.getCurrentAppBarTitle()) }
                )
            },
            bottomBar = {
                BottomAppBar {
                    Text("Bottom Bar")
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
                    AppNavigationHost(
                        appState = appState, // TODO: Analizar si es necesario usar appState
                        navigationViewModel = navigationViewModel
                    )
                }
            }
        }
    }
}