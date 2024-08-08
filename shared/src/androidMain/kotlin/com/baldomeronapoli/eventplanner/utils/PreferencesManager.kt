package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.constants.Preferences
import com.baldomeronapoli.eventplanner.data.utils.InitialRoute
import com.russhwolf.settings.Settings


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesManager actual constructor(private val settings: Settings) {

    actual fun getShownOnboarding(): Boolean =
        settings.getBoolean(Preferences.SHOW_ONBOARDING, true)


    actual fun setShownOnboarding() {
        settings.putBoolean(Preferences.SHOW_ONBOARDING, false)
    }

    actual fun setInitialRoute(email: String?) {
        if (email == null) {
            settings.remove(Preferences.MAIN_ROUTE)
        } else {
            settings.putString(Preferences.MAIN_ROUTE, email)
        }
    }

    actual fun getInitialRoute() =
        settings.getString(Preferences.MAIN_ROUTE, InitialRoute.AUTH)
}