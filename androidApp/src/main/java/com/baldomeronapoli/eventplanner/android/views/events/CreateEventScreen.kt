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
import com.baldomeronapoli.eventplanner.android.components.MyDatePickerDialog
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.components.richText.NRichTextEditor
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.utils.RequestMultiplePermissions
import com.baldomeronapoli.eventplanner.android.utils.getRequiredPermissions
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dev.gitlive.firebase.storage.File
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
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
                effect = effect
            )
        }

    }
}

@Composable
fun CreateEventContent(
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            it?.let { image ->
                imageUri = image
                onIntent(UiIntent.SetThumbnail(File(image)))
            }
        }
    )

    val painter = rememberAsyncImagePainter(model = imageUri ?: R.drawable.empty_thumbnail_event_2)
    var showDatePickerDialog by remember { mutableStateOf(false) }

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
                label = stringResource(id = R.string.event_name),
                keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
                onValueChange = {
                    onIntent(UiIntent.UpdateProperty("title", it))
                }
            )
        }

        item {
            NOutlinedTextField(
                value = uiState.event.date,
                label = stringResource(id = R.string.data_and_date),
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
            MyDatePickerDialog(
                show = showDatePickerDialog,
                value = uiState.event.date,
                onDismiss = { showDatePickerDialog = false },
                onDateSelected = {
                    onIntent(UiIntent.UpdateProperty("date", it))
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
            AutoComplete(
                value = uiState.event.games,
                query = uiState.queryGames,
                items = uiState.games,
                label = stringResource(id = R.string.event_s_games),
                onValueChange = {
                    onIntent(UiIntent.UpdateProperty("games", it))
                },
                onValueQueryChange = {
                    onIntent(UiIntent.UpdateQuery(it))
                }

            )
        }

        item {
            NOutlinedTextField(
                value = if (uiState.event.slots == null) "" else uiState.event.slots.toString(),
                label = stringResource(id = R.string.event_slot_number),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                onValueChange = {
                    val intValue = it.takeIf { it.isNotEmpty() }?.toIntOrNull()
                    onIntent(UiIntent.UpdateProperty("slots", intValue))
                }
            )
        }

        item {
            AddressMap(
                address = uiState.queryAddress,
                lat = uiState.event.place.coordinates.latitude,
                lng = uiState.event.place.coordinates.longitude
            ) { onIntent(UiIntent.UpdatePlace(it)) }
        }

        item {
            NButton(
                text = stringResource(id = R.string.create_event),
                enabled = uiState.event.isValid()
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
        )
    }
}
