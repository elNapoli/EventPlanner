package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.runtime.Composable
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold
import com.baldomeronapoli.eventplanner.android.theme.MyTheme

@Composable
fun NPreview(content: @Composable () -> Unit) {
    MyTheme {
        EmptyScaffold {
            content()
        }
    }
}