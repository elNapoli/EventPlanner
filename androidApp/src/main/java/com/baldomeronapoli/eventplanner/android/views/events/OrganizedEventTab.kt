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


@Composable
fun OrganizedEventTab(modifier: Modifier = Modifier) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(List(20) { it }) { item ->
            EventCardWide(onClick = {})
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOrganizedEventTabLight(modifier: Modifier = Modifier) {
    NPreview {

        OrganizedEventTab()
    }
}