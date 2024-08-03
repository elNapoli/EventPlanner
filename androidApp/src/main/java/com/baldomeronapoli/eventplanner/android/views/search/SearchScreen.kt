package com.baldomeronapoli.eventplanner.android.views.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.CategoryFilter
import com.baldomeronapoli.eventplanner.android.components.EventCard
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.EventsMock
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.presentation.models.EventUI

@Composable
fun SearchScreen(modifier: Modifier = Modifier, goToEventDetail: (EventUI) -> Unit) {
    Column {
        NOutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            },
            value = "",
            placeholder = stringResource(id = R.string.search_your_event),
            onValueChange = {})

        CategoryFilter(modifier = Modifier.padding(vertical = 16.dp))
        LazyColumn(
            modifier = Modifier.padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf(EventsMock.event)) { item ->
                EventCard(
                    modifier = Modifier.fillMaxWidth(),
                    event = item,
                    onClick = goToEventDetail
                )
            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewButtonTapLight(modifier: Modifier = Modifier) {
    NPreview {

        SearchScreen {}
    }
}