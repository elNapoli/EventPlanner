package com.baldomeronapoli.eventplanner.android.views.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.CategoryFilter
import com.baldomeronapoli.eventplanner.android.components.EventCard
import com.baldomeronapoli.eventplanner.android.components.EventCardWide
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.EventsMock
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.Gray60
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    goToEventDetail: (EventUI) -> Unit
) {
    LaunchedEffect(Unit) {
        onIntent(UiIntent.GetNearbyEvents(1))
    }
    Column {
        Text(
            text = stringResource(id = R.string.find_an_event_around_you),
            style = MaterialTheme.typography.titleMedium,
            color = Gray60,
        )
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.nearbyEvents) { item ->
                item?.let{
                    EventCard(
                        modifier = Modifier.width(260.dp),
                        event = item,
                        onClick = {  }
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.nearby_event),
                style = MaterialTheme.typography.headlineMedium,
                color = GrayTitle,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(id = R.string.view_all),
                color = Blue,
                style = MaterialTheme.typography.titleMedium
            )
        }
        CategoryFilter(modifier = Modifier.padding(vertical = 16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(listOf(EventsMock.event)) { item ->
                EventCardWide(onClick = goToEventDetail, event = EventsMock.event)
            }
        }
    }


}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewButtonTapLight(modifier: Modifier = Modifier) {
    NPreview {
        val effect: StateFlow<Effect?> = MutableStateFlow(null)
        HomeScreen(
            uiState = UiState(),
            effect = effect,
            onIntent = { },
        ) {

        }
    }
}