package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.mocks.EventsMock
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.Gray
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.domain.models.Event

@Composable
fun EventCardWide(modifier: Modifier = Modifier, event: Event, onClick: (Event) -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick(event) }
            .height(120.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray,
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp),

                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.thumbnailUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = null,
            )
            Column {
                Text(
                    text = "",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = GrayTitle

                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            OrganizerAvatar(
                modifier = Modifier.padding(start = 76.dp),
                size = 20.dp,
                name = event.host.userName
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


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewEventCardWideLight(modifier: Modifier = Modifier) {
    NPreview {
        EventCardWide(onClick = {}, event = EventsMock.event)
    }
}