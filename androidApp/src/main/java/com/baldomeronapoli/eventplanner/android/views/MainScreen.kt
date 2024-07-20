package com.baldomeronapoli.eventplanner.android.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.AppNavigationHost
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.NavigationViewModel
import com.baldomeronapoli.eventplanner.android.theme.MyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier, appState: AppState) {
    val navigationViewModel = koinViewModel<NavigationViewModel>()
    navigationViewModel.onEvent(
        NavigationEvent.OnSetContent(
            activityNavController = appState.navController,
        ) {
            appState.navController.popBackStack()
        })
    MyTheme {
        AppNavigationHost(
            appState = appState, // TODO: Analizar si es necesario usar appState
            navigationViewModel = navigationViewModel
        )
    }
}