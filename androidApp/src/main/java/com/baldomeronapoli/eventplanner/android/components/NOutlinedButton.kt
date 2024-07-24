package com.baldomeronapoli.eventplanner.android.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.theme.Gray100

@Composable
fun NOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    elevation: ButtonElevation? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit,
) {

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.outlinedButtonColors().copy(
            contentColor = Gray100,
        ),
        elevation = elevation,
        border = BorderStroke(1.dp, Gray100),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 14.dp),
        interactionSource = interactionSource,
        content = {
            Text(text = text, fontWeight = FontWeight.Bold)
        },
    )
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewNOutlinedButtonDark() {
    NPreview {
        NOutlinedButton(onClick = {}, text = "Hola")
        NOutlinedButton(onClick = {}, text = "Hola", enabled = false)
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNOutlinedButtonLight() {
    NPreview {
        NOutlinedButton(onClick = {}, text = "Hola")
        NOutlinedButton(onClick = {}, text = "Hola", enabled = false)
    }
}
