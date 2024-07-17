package com.baldomeronapoli.eventplanner.android.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList

@Composable
fun OnboardScreen() {

    val onboardPages = onboardPagesList

    val currentPage = remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {


        Image(
            modifier = Modifier.padding(top = 64.dp),
            painter = painterResource(id = onboardPages[currentPage.value].imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        OnBoardDetails(
            modifier = Modifier
                .padding(32.dp),
            currentPage = onboardPages[currentPage.value]
        )
        TabSelector(
            modifier = Modifier.width(100.dp),
            onboardPages = onboardPages,
            currentPage = currentPage.value
        ) { index ->
            currentPage.value = index
        }

        OnBoardNavButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            currentPage = currentPage.value,
            noOfPages = onboardPages.size
        ) {
            currentPage.value++
        }


    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOnboardScreenLight(modifier: Modifier = Modifier) {
    NPreview {
        OnboardScreen()
    }
}