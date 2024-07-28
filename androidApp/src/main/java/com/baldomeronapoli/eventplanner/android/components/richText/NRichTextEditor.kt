package com.baldomeronapoli.eventplanner.android.components.richText

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorColors
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NRichTextEditor(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 10,
    maxLength: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RichTextEditorDefaults.filledShape,
    colors: RichTextEditorColors = RichTextEditorDefaults.richTextEditorColors(),
    contentPadding: PaddingValues =
        if (label == null) {
            RichTextEditorDefaults.richTextEditorWithoutLabelPadding()
        } else {
            RichTextEditorDefaults.richTextEditorWithLabelPadding()
        },
    onTextChanged: (String) -> Unit = {}
) {
    val titleSize = MaterialTheme.typography.displaySmall.fontSize
    val subtitleSize = MaterialTheme.typography.titleLarge.fontSize
    val state = rememberRichTextState()
    LaunchedEffect(state.toHtml()) {
        onTextChanged(state.toHtml())
    }

    Column {
        EditorControls(
            onBoldClick = {
                state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
            },
            onItalicClick = {
                state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic))
            },
            onUnderlineClick = {
                state.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            },
            onTitleClick = {
                state.toggleSpanStyle(SpanStyle(fontSize = titleSize))
            },
            onSubtitleClick = {
                state.toggleSpanStyle(SpanStyle(fontSize = subtitleSize))
            },
            onTextColorClick = {
                state.toggleSpanStyle(SpanStyle(color = Color.Red))
            },
            onStartAlignClick = {
                state.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Start))
            },
            onEndAlignClick = {
                state.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.End))
            },
            onCenterAlignClick = {
                state.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
            }
        )


        RichTextEditor(
            modifier = Modifier
                .fillMaxWidth(),
            state = state,
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            supportingText = supportingText,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors,
            contentPadding = contentPadding,
            maxLength = maxLength
        )

    }
}