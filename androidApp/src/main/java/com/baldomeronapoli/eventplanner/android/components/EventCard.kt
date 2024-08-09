package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baldomero.napoli.common.formatters.TimeFormat
import com.baldomero.napoli.eventplannerevents.presentation.models.EventUI
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.mocks.EventsMock
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.Gray
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.theme.White
import com.baldomeronapoli.eventplanner.android.utils.toFormattedDateString


@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: EventUI,
    onClick: (EventUI) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onClick(event) }
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray,
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.thumbnail.name)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(White)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = event.startDate.toFormattedDateString(TimeFormat.DAY_ONLY),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Blue
                )
                Text(
                    text = event.startDate.toFormattedDateString(TimeFormat.MONTH_SHORT_LETTER),
                    style = MaterialTheme.typography.titleSmall,
                    color = Blue,
                )
            }
        }
        Text(
            text = event.title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
            color = GrayTitle
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = event.startDate.toFormattedDateString(TimeFormat.TIME_FORMAT_24_HOUR),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Free",
                color = Blue,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewEventCardLight(modifier: Modifier = Modifier) {
    NPreview {

        EventCard(event = EventsMock.event) {}
    }
}