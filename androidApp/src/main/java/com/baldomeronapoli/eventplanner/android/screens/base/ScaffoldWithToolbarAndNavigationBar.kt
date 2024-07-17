package com.baldomeronapoli.eventplanner.android.screens.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldWithToolbarAndNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {

        },
        bottomBar = {
            BottomAppBar {
                Text("Bottom Bar")
            }
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                content()
            }
        }
    }
}