package com.baldomeronapoli.eventplanner.utils

import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SharePreferences actual constructor() : KoinComponent {
    private val sharedPrefs by inject<SharedPreferences>()
    actual fun getSettings(): Settings =
        SharedPreferencesSettings(sharedPrefs)
}