package com.baldomeronapoli.eventplanner.android.views.events

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.EventCardWide
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.domain.models.Event


@Composable
fun EventTab(modifier: Modifier = Modifier, events: List<Event> = emptyList()) {
    if (events.isEmpty()) {
        EmptyEvent()
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(events) { event ->
                EventCardWide(event = event, onClick = {})
            }
        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOrganizedEventTabLight(modifier: Modifier = Modifier) {
    NPreview {

        EventTab()
    }
}