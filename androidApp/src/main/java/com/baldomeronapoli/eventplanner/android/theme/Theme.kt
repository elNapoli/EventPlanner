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
            primary = Orange,
            onPrimary = White,
            secondary = SecondaryDark,
            tertiary = TertiaryDark
        )
    } else {
        lightColorScheme(
            primary = Orange,
            secondary = SecondaryLight,
            tertiary = TertiaryLight,
            error = Red,
            onError = Red,
            onErrorContainer = Red.copy(alpha = 0.20F),
            onSurface = GrayTitle,
            onBackground = Gray60,
            onSurfaceVariant = Gray100
        )
    }


    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
