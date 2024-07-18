package com.baldomeronapoli.eventplanner.utils

import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

lateinit var sharedPrefsForPlatformDependencies: SharedPreferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformDependencies actual constructor() {
    actual fun getSettings(): Settings =
        SharedPreferencesSettings(sharedPrefsForPlatformDependencies)
}