package com.baldomeronapoli.eventplanner.android.views.events

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.components.NPreview

@Composable
fun CreateEventScreen(modifier: Modifier = Modifier) {
    Text("asdfasdf")
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCreateEventScreenLight(modifier: Modifier = Modifier) {
    NPreview {
        CreateEventScreen()
    }
}