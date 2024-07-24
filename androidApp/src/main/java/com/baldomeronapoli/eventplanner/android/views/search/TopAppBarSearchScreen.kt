package com.baldomeronapoli.eventplanner.android.views.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SwipeDownAlt
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.theme.Gray60
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSearchScreen(modifier: Modifier = Modifier) {
    TopAppBar(

        title = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(id = R.string.my_location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray60
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = null)
                    Text(text = "Kediri, East Java", color = GrayTitle, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.titleLarge)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)

                }
            }
        }

    )
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewTopAppBarSearchScreenLight(modifier: Modifier = Modifier) {
    NPreview {

        TopAppBarSearchScreen()
    }
}