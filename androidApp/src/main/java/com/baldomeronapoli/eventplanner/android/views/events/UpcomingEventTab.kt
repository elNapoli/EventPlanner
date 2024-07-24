package com.baldomeronapoli.eventplanner.android.views.events

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.components.EventCardWide
import com.baldomeronapoli.eventplanner.android.components.NPreview


@Composable
fun UpcomingEventTab(modifier: Modifier = Modifier) {
    EventCardWide {

    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewUpcomingEventTabLight(modifier: Modifier = Modifier) {
    NPreview {

        UpcomingEventTab()
    }
}