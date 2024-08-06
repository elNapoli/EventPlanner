package com.baldomeronapoli.eventplanner.android.views.eventDetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.AddressMap
import com.baldomeronapoli.eventplanner.android.components.AlertDialog
import com.baldomeronapoli.eventplanner.android.components.LoadingCenterWrapper
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.components.OrganizerAvatar
import com.baldomeronapoli.eventplanner.android.theme.Blue
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.utils.toRichHtmlString
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EventDetailScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onIntent: (UiIntent) -> Unit,
    effect: StateFlow<Effect?>,
    eventId: String,
    goBack: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        onIntent(UiIntent.GetEventById(eventId))
    }
    LoadingCenterWrapper(
        isLoading = uiState.isLoading
    ) {
        if (uiState.feedbackUI != null) {
            AlertDialog(
                onConfirmation = goBack,
                dialogTitle = uiState.feedbackUI!!.title,
                dialogText = uiState.feedbackUI!!.message,
                feedbackUIType = uiState.feedbackUI!!.type,
                confirmText = stringResource(id = R.string.go_back),
            )
        }
        if (uiState.currentEvent != null) {

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(MaterialTheme.shapes.small),

                    model = ImageRequest.Builder(LocalContext.current)
                        .data(uiState.currentEvent!!.thumbnail.name)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = null,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Status")
                    Text(
                        text = "Free",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.data_and_date),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                        color = GrayTitle

                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                        // Text(text = uiState.currentEvent!!.date.toFormattedDateString("EEEE dd 'de' MMMM 'del' yyyy"))
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(imageVector = Icons.Default.AccessTime, contentDescription = null)
                        //   Text(text = uiState.currentEvent!!.date.toFormattedDateString("HH:mm"))
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.description),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                        color = GrayTitle

                    )
                    Text(
                        text = uiState.currentEvent!!.description.toRichHtmlString(),
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Column(
                    modifier = Modifier.padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.event_organizer),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                        color = GrayTitle

                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OrganizerAvatar(name = uiState.currentEvent!!.host.email)
                        Text(
                            text = stringResource(id = R.string.chat),
                            color = Blue,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(top = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.location),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold,
                        color = GrayTitle

                    )


                    AddressMap(
                        address = uiState.currentEvent!!.address.street,
                        previewMode = true,
                        lat = uiState.currentEvent!!.address.latitude,
                        lng = uiState.currentEvent!!.address.longitude,
                    ) { onIntent(UiIntent.UpdatePlace(it)) }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        NButton(
                            modifier = Modifier.padding(vertical = 32.dp),
                            text = stringResource(id = R.string.booking_now)
                        ) {

                        }
                    }
                }
            }
        }
    }

}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewEventDetailScreenLight(modifier: Modifier = Modifier) {
    val effect: StateFlow<Effect?> = MutableStateFlow(null)

    NPreview {
        EventDetailScreen(
            uiState = UiState(),
            onIntent = {},
            effect = effect,
            eventId = ""
        )
    }
}