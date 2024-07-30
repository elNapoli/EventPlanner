package com.baldomeronapoli.eventplanner.utils

import android.content.SharedPreferences
import com.baldomeronapoli.eventplanner.constants.Preferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SharePreferences actual constructor() : KoinComponent {
    private val sharedPrefs by inject<SharedPreferences>()
    actual val settings: Settings =
        SharedPreferencesSettings(sharedPrefs)

    actual fun getShownOnboarding(): Boolean =
        settings.getBoolean(Preferences.SHOW_ONBOARDING, true)


    actual fun setShownOnboarding() {
        settings.putBoolean(Preferences.SHOW_ONBOARDING, false)
    }

    actual fun setEmailCurrentUser(email: String?) {
        if (email == null) {
            settings.remove(Preferences.EMAIL_CURRENT_USER)
        } else {
            settings.putString(Preferences.EMAIL_CURRENT_USER, email)

        }
    }

    actual fun getEmailCurrentUser() =
        settings.getStringOrNull(Preferences.EMAIL_CURRENT_USER)
}