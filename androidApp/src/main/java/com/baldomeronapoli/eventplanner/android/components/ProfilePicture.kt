package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baldomeronapoli.eventplanner.android.theme.Orange

@Composable
fun ProfilePicture(
    letter: Char,
    size: Dp = 40.dp,
    backgroundColor: Color = Orange, // Naranja
    textColor: Color = Color.White,
    textStyle: TextStyle = TextStyle(fontSize = 24.sp)
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = backgroundColor, shape = CircleShape)
            .graphicsLayer { alpha = 1f },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = textColor,
            style = textStyle,
            fontSize = (size / 2).value.sp
        )
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewAlertDialogLight() {
    NPreview {

        ProfilePicture(
            letter = 'A'
        )
    }
}