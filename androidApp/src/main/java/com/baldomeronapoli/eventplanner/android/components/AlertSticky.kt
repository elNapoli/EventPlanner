package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.Success
import com.baldomeronapoli.eventplanner.android.theme.Warning
import com.baldomeronapoli.eventplanner.domain.models.AlertType

@Composable
fun AlertSticky(
    modifier: Modifier = Modifier,
    alertType: AlertType = AlertType.SUCCESS,
    text: String
) {
    val (icon, color, backgroundColor) = when (alertType) {
        AlertType.SUCCESS -> Triple(Icons.Default.CheckCircle, Success, Success.copy(alpha = 0.2f))
        AlertType.ERROR -> Triple(Icons.Default.Error, MaterialTheme.colorScheme.onError, MaterialTheme.colorScheme.error)
        AlertType.WARNING -> Triple(Icons.Default.Warning, Warning, Warning.copy(alpha = 0.2f))
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
        Text(
            text = text,
            color = color
        )
    }
}



@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAlertStickyLight(modifier: Modifier = Modifier) {
    NPreview {

        AlertSticky(
            alertType = AlertType.SUCCESS,
            text = "esto es una psdfasdfasdf"
        )
        AlertSticky(
            alertType = AlertType.WARNING,
            text = "esto es una psdfasdfasdf"


        )
        AlertSticky(
            alertType = AlertType.ERROR,
            text = "esto es una psdfasdfazcadasdfasdfasdfasdfasdfsdf"


        )
    }
}