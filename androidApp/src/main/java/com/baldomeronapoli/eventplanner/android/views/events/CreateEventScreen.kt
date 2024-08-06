package com.baldomeronapoli.eventplanner.android.views.events

import AutoComplete
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.AddressMap
import com.baldomeronapoli.eventplanner.android.components.AlertDialog
import com.baldomeronapoli.eventplanner.android.components.AlertSticky
import com.baldomeronapoli.eventplanner.android.components.MyDatePickerDialog
import com.baldomeronapoli.eventplanner.android.components.MyTimePickerDialog
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.components.richText.NRichTextEditor
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.utils.RequestMultiplePermissions
import com.baldomeronapoli.eventplanner.android.utils.getRequiredPermissions
import com.baldomeronapoli.eventplanner.android.utils.toFormattedDateString
import com.baldomeronapoli.eventplanner.android.utils.uriToByteArray
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUIType
import com.baldomeronapoli.eventplanner.utils.TimeConstant
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    goBack: () -> Unit,
) {
    RequestMultiplePermissions(
        getRequiredPermissions()
    ) {
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            CreateEventContent(
                uiState = uiState,
                onIntent = onIntent,
                effect = effect,
                goBack = goBack
            )
        }

    }
}


@Composable
fun CreateEventContent(
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    goBack: () -> Unit,
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var context = LocalContext.current
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            it?.let { image ->
                imageUri = image
                onIntent(UiIntent.SetThumbnailByArray(uriToByteArray(context, imageUri!!)))
            }
        }
    )
    val painter = rememberAsyncImagePainter(model = imageUri ?: R.drawable.empty_thumbnail_event_2)
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var showTimePickerDialog by remember { mutableStateOf(false) }
    if (uiState.feedbackUI != null) {
        AlertDialog(
            onConfirmation = goBack,
            dialogTitle = uiState.feedbackUI!!.title,
            dialogText = uiState.feedbackUI!!.message,
            feedbackUIType = uiState.feedbackUI!!.type,
            confirmText = stringResource(id = R.string.confirm),
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clickable {
                            singlePhotoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.upload_thumbnail),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    color = GrayTitle,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        item {
            NOutlinedTextField(
                value = uiState.event.title,
                maxLines = 1,
                label = stringResource(id = R.string.event_name),
                keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = {
                    onIntent(UiIntent.UpdateProperty("title", it))
                }
            )
        }

        item {
            NOutlinedTextField(
                value = uiState.event.startDate.toFormattedDateString(),
                label = stringResource(id = R.string.date),
                maxLines = 1,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePickerDialog = true }) {
                        Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null)
                    }
                }
            )
        }


        item {
            NOutlinedTextField(
                value = uiState.event.startDate.toFormattedDateString(TimeConstant.TIME_FORMAT_24_HOUR),
                label = stringResource(id = R.string.hour),
                maxLines = 1,
                onValueChange = { },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showTimePickerDialog = true }) {
                        Icon(imageVector = Icons.Default.Timer, contentDescription = null)
                    }
                }
            )
        }

        item {
            MyDatePickerDialog(
                show = showDatePickerDialog,
                value = uiState.event.startDate,
                onDismiss = { showDatePickerDialog = false },
                onDateSelected = {
                    onIntent(UiIntent.UpdateStartDateEvent(it))
                })
        }

        item {
            MyTimePickerDialog(
                show = showTimePickerDialog,
                value = uiState.event.startDate,
                onDismiss = { showTimePickerDialog = false },
                onTimeSelected = {
                    onIntent(UiIntent.UpdateStartDateEvent(it))
                })
        }

        item {
            NRichTextEditor(
                label = { Text(text = stringResource(id = R.string.description)) },
            ) {
                onIntent(UiIntent.UpdateProperty("description", it))
            }
        }

        item {
            AlertSticky(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.develop_app),
                feedbackUIType = FeedbackUIType.WARNING
            )
            AutoComplete(
                value = uiState.event.boardgames.getOrNull(0)?.name ?: "",
                query = uiState.queryGames,
                items = uiState.boardGameBGG,
                label = stringResource(id = R.string.event_s_games),
                onValueChange = {
                    onIntent(UiIntent.AddGameIntoEvent(it))
                },
                onValueQueryChange = {
                    onIntent(UiIntent.UpdateQuery(it))
                }

            )
        }

        item {
            NOutlinedTextField(
                value = uiState.event.slots.toString(),
                label = stringResource(id = R.string.event_slot_number),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    if (it.isNotBlank() && it.length <= 2) {
                        onIntent(UiIntent.UpdateProperty("slots", it))
                    }
                }
            )
        }

        item {
            AddressMap(
                address = uiState.queryAddress,
                lat = uiState.event.address.latitude,
                lng = uiState.event.address.longitude
            ) { onIntent(UiIntent.UpdatePlace(it)) }
        }

        item {
            NButton(
                text = stringResource(id = R.string.create_event),
                enabled = true
            ) {
                onIntent(UiIntent.CreateEvent)
            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCreateEventScreenLight(modifier: Modifier = Modifier) {

    val effect: StateFlow<Effect?> = MutableStateFlow(null)
    NPreview {
        CreateEventContent(
            uiState = UiState(),
            effect = effect,
            onIntent = { },
            goBack = {}
        )
    }
}
