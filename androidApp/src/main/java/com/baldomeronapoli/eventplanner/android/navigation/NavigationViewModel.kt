package com.baldomeronapoli.eventplanner.android.navigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

open class NavigationViewModel : ViewModel() {

    private lateinit var onBackPressed: () -> Unit

    lateinit var activityNavController: NavHostController

    fun onEvent(event: NavigationEvent) {
        when (event) {

            is NavigationEvent.OnSetContent -> {
                activityNavController = event.activityNavController
                onBackPressed = event.onBackPressed
            }

            is NavigationEvent.OnBack -> onBackPressed()

            is NavigationEvent.OnNavigateToScreen -> {
                activityNavController.navigate(event.route.path) {
                    event.popUpToRoute?.let {
                        popUpTo(it) {
                            inclusive = event.inclusive
                        }
                    }

                }
            }

        }
    }

}