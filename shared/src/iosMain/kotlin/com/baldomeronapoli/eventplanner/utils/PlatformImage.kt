package com.baldomeronapoli.eventplanner.utils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformImage(private val filePath: String) {
    actual val path: String
        get() = filePath
}
