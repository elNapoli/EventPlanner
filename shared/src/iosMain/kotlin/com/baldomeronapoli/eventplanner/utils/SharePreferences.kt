package com.baldomeronapoli.eventplanner.utils

import com.baldomeronapoli.eventplanner.constants.Preferences
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SharePreferences actual constructor() {
    actual val settings: Settings = NSUserDefaultsSettings(NSUserDefaults())
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