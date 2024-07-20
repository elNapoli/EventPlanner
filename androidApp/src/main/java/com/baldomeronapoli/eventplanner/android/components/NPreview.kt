package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.MyTheme
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold

@Composable
fun NPreview(content: @Composable () -> Unit) {
    MyTheme {
        EmptyScaffold {
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                content()
            }
        }
    }
}