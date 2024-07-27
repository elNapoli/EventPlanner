package com.baldomeronapoli.eventplanner.utils

import android.net.Uri

actual class PlatformImage(private val uri: Uri) {
    actual val path: String
        get() = uri.toString() // O utiliza una ruta temporal si es necesario
}
