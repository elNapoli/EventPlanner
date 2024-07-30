package com.baldomeronapoli.eventplanner.utils

import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SharePreferences() {
    val settings: Settings
    fun getShownOnboarding(): Boolean
    fun setShownOnboarding()
    fun setEmailCurrentUser(email: String?)
    fun getEmailCurrentUser(): String?
}