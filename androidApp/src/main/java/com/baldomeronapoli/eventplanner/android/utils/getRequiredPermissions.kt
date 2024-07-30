package com.baldomeronapoli.eventplanner.android.utils

import android.Manifest
import android.os.Build

fun getRequiredPermissions(): List<String> {
    val permissions = mutableListOf<String>()

    // Permisos comunes
    permissions.add(Manifest.permission.INTERNET)
    permissions.add(Manifest.permission.ACCESS_NETWORK_STATE)
    permissions.add(Manifest.permission.CAMERA)
    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
    permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
        permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
    }

    return permissions
}