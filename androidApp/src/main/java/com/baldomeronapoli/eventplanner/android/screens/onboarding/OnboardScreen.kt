package com.baldomeronapoli.eventplanner.android.screens.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen() {
    val onboardPages = onboardPagesList
    val pagerState = rememberPagerState(pageCount = { onboardPages.size })
    var targetPage by remember { mutableStateOf(pagerState.currentPage) }

    // Update pager state in composable body
    LaunchedEffect(targetPage) {
        if (targetPage != pagerState.currentPage) {
            pagerState.scrollToPage(targetPage)
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            val currentPage = onboardPages[pagerState.currentPage]
            OnboardScreenPage(currentPage.title, currentPage.description, currentPage.imageRes)
        }
        TabSelector(
            modifier = Modifier.width(100.dp),
            onboardPages = onboardPages,
            currentPage = pagerState.currentPage
        ) { index ->
            targetPage = index
        }

        OnBoardNavButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            currentPage = pagerState.currentPage,
            noOfPages = onboardPages.size
        ) {
            targetPage++
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