package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NTopBar(modifier: Modifier = Modifier, title: String, onNavigationIcon: (() -> Unit)? = null) {
    TopAppBar(
        navigationIcon = {
            onNavigationIcon?.let {
                IconButton(onClick = { it.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = GrayTitle
                )
            }
        },
    )
}