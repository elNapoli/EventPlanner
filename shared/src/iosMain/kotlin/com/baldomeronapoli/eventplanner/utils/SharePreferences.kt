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
}