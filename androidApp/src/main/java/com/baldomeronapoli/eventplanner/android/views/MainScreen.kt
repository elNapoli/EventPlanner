package com.baldomeronapoli.eventplanner.android.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.baldomeronapoli.eventplanner.android.AppState
import com.baldomeronapoli.eventplanner.android.navigation.AppNavigationHost
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.NavigationViewModel
import com.baldomeronapoli.eventplanner.android.theme.MyTheme
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract
import org.koin.androidx.compose.koinViewModel


val LocalNavigationViewModel =
    compositionLocalOf<NavigationViewModel> { error("No NavigationViewModel provided") }

@Composable
fun MainScreen(
    modifier: Modifier = Modifier, appState: AppState,
    state: AuthContract.UiState
) {
    val navigationViewModel = koinViewModel<NavigationViewModel>()
    navigationViewModel.onEvent(
        NavigationEvent.OnSetContent(
            activityNavController = appState.navController,
        ) {
            appState.navController.popBackStack()
        })
    CompositionLocalProvider(LocalNavigationViewModel provides navigationViewModel) {
        MyTheme {
            AppNavigationHost(
                appState = appState, // TODO: Analizar si es necesario usar appState
                state = state,
                navigationViewModel = navigationViewModel
            )
        }
    }

}