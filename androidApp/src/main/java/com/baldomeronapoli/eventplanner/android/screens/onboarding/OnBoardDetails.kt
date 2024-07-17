package com.baldomeronapoli.eventplanner.android.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle

@Composable
fun OnBoardDetails(
    modifier: Modifier = Modifier, title: String, description: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = GrayTitle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOnBoardDetailsLight(modifier: Modifier = Modifier) {
    NPreview {
        OnBoardDetails(title = onboardPagesList[0].title, description = onboardPagesList[0].description)
    }
}