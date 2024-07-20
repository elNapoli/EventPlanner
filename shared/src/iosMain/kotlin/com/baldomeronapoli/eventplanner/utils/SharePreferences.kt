package com.baldomeronapoli.eventplanner.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SharePreferences actual constructor() {
    actual fun getSettings(): Settings = NSUserDefaultsSettings(NSUserDefaults())
}