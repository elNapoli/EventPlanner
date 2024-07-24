package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.Gray
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle

@Composable
fun EventCardWide(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .clickable { onClick() }
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
            Image(
                modifier = Modifier
                    .size(60.dp),
                painter = painterResource(id = R.drawable.empty_thumbnail_event),
                contentDescription = null
            )
            Column {
                Text(text = "Today 10.00 - 12.00", style = MaterialTheme.typography.titleSmall)
                Text(
                    text = "Shawn Mendes The Virtual Tour 2021 indes The Virtual Tour 2021 indes The Virtual Tour 2021 indes The Virtual Tour 2021 indes The Virtual Tour 2021 in Germany ",
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
            Row(
                modifier = Modifier.padding(start = 76.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfilePicture(
                    letter = "A",
                    modifier = Modifier
                        .size(14.dp)
                )
                Text(text = "Microsoft")
            }

            Text(
                text = "$100",
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

        EventCardWide(){}
    }
}