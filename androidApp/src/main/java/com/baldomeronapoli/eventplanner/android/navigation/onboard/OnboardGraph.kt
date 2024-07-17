package com.baldomeronapoli.eventplanner.android.navigation.onboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold
import com.baldomeronapoli.eventplanner.android.views.onboarding.OnboardScreen

fun NavGraphBuilder.onboardGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = OnboardRoute.Index.path,
        route = MainRoute.Onboard.path
    ) {
        composable(OnboardRoute.Index.path) {
            EmptyScaffold {
                OnboardScreen()
            }
        }
    }
}
