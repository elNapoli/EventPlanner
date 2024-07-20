package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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