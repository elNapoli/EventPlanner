package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.MyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NPreview(content: @Composable () -> Unit) {
    MyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Preview") }
                )
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
}