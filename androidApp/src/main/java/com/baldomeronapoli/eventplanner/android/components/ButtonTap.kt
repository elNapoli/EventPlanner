package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.Gray100
import com.baldomeronapoli.eventplanner.android.theme.Gray20
import com.baldomeronapoli.eventplanner.android.theme.Orange
import com.baldomeronapoli.eventplanner.android.theme.White
import java.util.Locale

@Composable
fun ButtonTap(modifier: Modifier = Modifier, isActive: Boolean) {

    var colorText = White
    var backgroundView = Orange
    if (isActive) {
        colorText = Gray100
        backgroundView = Gray20
    }
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(backgroundView)
            .padding(vertical = 8.dp, horizontal = 24.dp)
    ) {
        Text(
            text = "online dlass".replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            color = colorText,
            style = MaterialTheme.typography.titleMedium
        )
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewButtonTapLight(modifier: Modifier = Modifier) {
    NPreview {

        ButtonTap(isActive = true)
        ButtonTap(isActive = false)
    }
}