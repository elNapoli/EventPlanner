package com.baldomeronapoli.eventplanner.android.navigation.auth

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.baldomeronapoli.eventplanner.android.navigation.NavigationEvent
import com.baldomeronapoli.eventplanner.android.navigation.route.MainRoute
import com.baldomeronapoli.eventplanner.android.views.auth.SignInScreen
import com.baldomeronapoli.eventplanner.android.views.auth.SuccessfulSignUpScreen
import com.baldomeronapoli.eventplanner.presentation.auth.AuthViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.authGraph(
    onNavigationEvent: (NavigationEvent) -> Unit,
) {
    navigation(
        startDestination = AuthRoute.SignIn.path,
        route = MainRoute.Auth.path
    ) {
        composable(AuthRoute.SignIn.path) {
            val viewmodel: AuthViewModel = koinViewModel()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            SignInScreen(
                uiState = uiState.value,
                effect = viewmodel.sideEffect,
                onAction = viewmodel::handleIntent,
            )
        }

        composable(AuthRoute.SignUp.path) {
        }

        composable(AuthRoute.ValidateEmail.path) {
        }

        composable(AuthRoute.SuccessfulSignUp.path) {
            SuccessfulSignUpScreen()
        }
    }
}
