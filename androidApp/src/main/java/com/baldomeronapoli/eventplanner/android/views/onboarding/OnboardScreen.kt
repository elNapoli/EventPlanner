package com.baldomeronapoli.eventplanner.android.views.onboarding

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
import com.baldomeronapoli.eventplanner.android.components.CollectEffect
import com.baldomeronapoli.eventplanner.android.mocks.onboardPagesList
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.Effect
import com.baldomeronapoli.eventplanner.presentation.onBoard.OnboardContract.UiIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardScreen(
    modifier: Modifier = Modifier,
    effect: StateFlow<Effect?>,
    onIntent: (UiIntent) -> Unit,
    goToAuth: () -> Unit
) {
    val onboardPages = onboardPagesList
    val pagerState = rememberPagerState(pageCount = { onboardPages.size })
    var targetPage by remember { mutableStateOf(pagerState.currentPage) }

    LaunchedEffect(targetPage) {
        if (targetPage != pagerState.currentPage) {
            pagerState.scrollToPage(targetPage)
        }
    }

    CollectEffect(effect) {
        when (it) {
            Effect.GoToAuthGraph -> goToAuth()
        }
    }
    EmptyScaffold{
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
                OnboardPageView(currentPage.title, currentPage.description, currentPage.imageRes)
            }
            TabSelectorView(
                modifier = Modifier.width(100.dp),
                onboardPages = onboardPages,
                currentPage = pagerState.currentPage
            ) { index ->
                targetPage = index
            }

            OnBoardNavButtonView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                currentPage = pagerState.currentPage,
                noOfPages = onboardPages.size,
                onNextClicked = { targetPage++ },
                finishOnboarding = { onIntent(UiIntent.CompleteOnboarding) }
            )
        }
    }


}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewOnboardScreenLight(modifier: Modifier = Modifier) {
    val effect: StateFlow<Effect> =
        MutableStateFlow(Effect.GoToAuthGraph)

    OnboardScreen(
        effect = effect,
        onIntent = { }
    ) {}
}