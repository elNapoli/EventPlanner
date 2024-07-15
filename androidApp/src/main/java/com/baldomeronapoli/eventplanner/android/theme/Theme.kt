package com.baldomeronapoli.eventplanner.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = PrimaryDark,
            secondary = SecondaryDark,
            tertiary = TertiaryDark
        )
    } else {
        lightColorScheme(
            primary = PrimaryLight,
            secondary = SecondaryLight,
            tertiary = TertiaryLight
        )
    }


    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}