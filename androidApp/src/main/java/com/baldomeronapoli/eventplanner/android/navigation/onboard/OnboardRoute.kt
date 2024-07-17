package com.baldomeronapoli.eventplanner.android.navigation.onboard

import com.baldomeronapoli.eventplanner.android.navigation.route.Route

sealed class OnboardRoute(override val path: String, override val title: String) :
    Route(path, title) {
    data object Index : OnboardRoute("onboard-index", "Onboarding")
}