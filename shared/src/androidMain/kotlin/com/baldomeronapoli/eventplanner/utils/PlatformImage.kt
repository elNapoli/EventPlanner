package com.baldomeronapoli.eventplanner.utils

import android.net.Uri

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformImage(private val uri: Uri) {
    actual val path: String
        get() = uri.toString() // O utiliza una ruta temporal si es necesario
}
