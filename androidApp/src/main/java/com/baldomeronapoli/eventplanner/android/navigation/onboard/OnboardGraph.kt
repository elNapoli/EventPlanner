package com.baldomeronapoli.eventplanner.android.navigation.onboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.onboarding.OnboardScreen
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnBoardViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.onboardGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = OnboardRoute.Index.path,
        route = MainRoute.Onboard.path
    ) {

        composable(OnboardRoute.Index.path) {
            val viewmodel: OnBoardViewModel = koinViewModel()
            OnboardScreen(
                effect = viewmodel.effect,
                onIntent = viewmodel::sendIntent,
                goToAuth = {
                    onNavigationEvent(
                        NavigationEvent.OnNavigateToScreen(
                            route = MainRoute.Auth,
                            popUpToRoute = MainRoute.Onboard.path,
                            inclusive = true
                        )
                    )
                }
            )
        }
    }
}
