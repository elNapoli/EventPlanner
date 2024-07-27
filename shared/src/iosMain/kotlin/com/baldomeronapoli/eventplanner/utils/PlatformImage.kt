package com.baldomeronapoli.eventplanner.utils

actual class PlatformImage(private val filePath: String) {
    actual val path: String
        get() = filePath
}
