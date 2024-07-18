package com.baldomeronapoli.eventplanner.android.views.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.DetailComponent
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList

@Composable
fun OnboardPageView(title: String, description: String, imageRes: Int) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            modifier = Modifier.padding(top = 64.dp),
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        DetailComponent(
            modifier = Modifier
                .padding(32.dp),
            title = title,
            description = description
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOnboardScreenPageLight(modifier: Modifier = Modifier) {
    NPreview {
        val data = onboardPagesList[0]
        OnboardPageView(data.title, data.description, data.imageRes)
    }
}