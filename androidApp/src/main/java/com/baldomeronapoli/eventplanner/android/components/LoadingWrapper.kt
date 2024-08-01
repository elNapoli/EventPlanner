package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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



@Composable
fun LoadingCenterWrapper(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    content: @Composable () -> Unit,
) {

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator()
        }
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