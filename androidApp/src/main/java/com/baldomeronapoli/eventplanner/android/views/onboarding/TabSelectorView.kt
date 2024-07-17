package com.baldomeronapoli.eventplanner.android.views.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList
import com.baldomeronapoli.eventplanner.android.models.OnboardPage
import com.baldomeronapoli.eventplanner.android.theme.Gray80


@Composable
fun TabSelectorView(
    modifier: Modifier = Modifier,
    onboardPages: List<OnboardPage>,
    currentPage: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        onboardPages.forEachIndexed { index, _ ->
            Box(
                modifier = Modifier
                    .clickable {
                        onTabSelected(index)
                    }
                    .size(8.dp)
                    .background(
                        color = if (index == currentPage) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.primary.copy(alpha = 0.2F), shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewTabSelectorLight(modifier: Modifier = Modifier) {
    val onboardPages = onboardPagesList

    NPreview {
        TabSelectorView(
            onboardPages = onboardPages,
            currentPage = 0
        ) {
        }
    }
}
