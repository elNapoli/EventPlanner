package com.baldomeronapoli.eventplanner.android.views.events

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.views.onboarding.OnboardPageView

@Composable
fun MyEventsScreen(modifier: Modifier = Modifier) {
    Column {
        OnboardPageView(
            imageRes = R.drawable.empty_event,
            title = stringResource(id = R.string.oops_empty_list_event),
            description = stringResource(id = R.string.empty_event_description)
        )
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewMyEventsScreenLight(modifier: Modifier = Modifier) {
    NPreview {

        MyEventsScreen()
    }
}