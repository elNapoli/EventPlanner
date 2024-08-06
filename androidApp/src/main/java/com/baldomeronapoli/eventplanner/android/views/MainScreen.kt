package com.baldomeronapoli.eventplanner.android.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.compose.rememberNavController
import com.baldomeronapoli.eventplanner.android.navigation.AppNavigationHost
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.NavigationViewModel
import com.baldomeronapoli.eventplanner.android.theme.MyTheme
import org.koin.androidx.compose.koinViewModel


val LocalNavigationViewModel =
    compositionLocalOf<NavigationViewModel> { error("No NavigationViewModel provided") }


@Composable
fun MainScreen() {
    val navigationViewModel = koinViewModel<NavigationViewModel>()
    val navController = rememberNavController()
    navigationViewModel.onEvent(
        NavigationEvent.OnSetContent(
            activityNavController = navController,
        ) {
            navController.popBackStack()
        })
    CompositionLocalProvider(LocalNavigationViewModel provides navigationViewModel) {
        MyTheme {
            AppNavigationHost(
                navController = navController,
                navigationViewModel = navigationViewModel
            )
        }
    }

}