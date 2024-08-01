package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrganizerAvatar(modifier: Modifier = Modifier, size: Dp = 40.dp, name: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        name.getOrNull(0)?.let {
            ProfilePicture(
                letter = it.uppercaseChar(),
                size = size
            )
        }
        Text(text = name)
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOrganizerAvatarLight(modifier: Modifier = Modifier) {
    NPreview {
        OrganizerAvatar(name = "Baldomero")
        OrganizerAvatar(
            modifier = Modifier.padding(start = 76.dp),
            size = 20.dp,
            name = "Baldomero"
        )
    }
}