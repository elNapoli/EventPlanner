package com.baldomeronapoli.eventplanner.android.views.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NButton

@Composable
fun OnBoardNavButtonView(
    modifier: Modifier = Modifier, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit
) {
    NButton(
        onClick = {
            if (currentPage < noOfPages - 1) {
                onNextClicked()
            } else {
                // Handle onboarding completion
            }
        }, modifier = modifier,
        text = if (currentPage < noOfPages - 1) stringResource(id = R.string.next) else stringResource(
            R.string.get_started
        )
    )
}