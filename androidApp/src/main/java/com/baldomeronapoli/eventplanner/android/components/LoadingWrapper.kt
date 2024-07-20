package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingWrapper(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {

    if (isLoading) {
        CircularProgressIndicator(modifier = modifier)
    } else {
        content()
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewLoadingWrapperLight(modifier: Modifier = Modifier) {
    NPreview {
        LoadingWrapper(isLoading = true) {}
    }
}