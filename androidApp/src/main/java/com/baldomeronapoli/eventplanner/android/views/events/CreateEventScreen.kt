package com.baldomeronapoli.eventplanner.android.views.events

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.location.Geocoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.NOutlinedTextField
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.components.richText.NRichTextEditor
import com.baldomeronapoli.eventplanner.android.theme.GrayTitle
import com.baldomeronapoli.eventplanner.android.utils.RequestMultiplePermissions
import com.baldomeronapoli.eventplanner.android.utils.getRequiredPermissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEventScreen(modifier: Modifier = Modifier) {
    RequestMultiplePermissions(
        getRequiredPermissions()
    ) {
        CreateEventContent()
    }
}

@Composable
fun CreateEventContent(modifier: Modifier = Modifier) {
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {}
    )
    val state = rememberRichTextState()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
                painter = painterResource(id = R.drawable.empty_thumbnail_event_2),
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

        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.event_name),
            onValueChange = {}
        )
        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.data_and_date),
            onValueChange = {}
        )
        NRichTextEditor(
            label = { Text(text = stringResource(id = R.string.description)) },
            state = state
        )
        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.event_s_games),
            onValueChange = {}
        )
        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.event_slot_number),
            onValueChange = {}
        )
        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.event_price),
            onValueChange = {}
        )
        NOutlinedTextField(
            value = "",
            label = stringResource(id = R.string.event_private),
            onValueChange = {}
        )
        AddressMap()
    }
}

suspend fun geocodeAddress(context: Context, address: String): LatLng? {
    return withContext(Dispatchers.IO) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val address = addresses[0]
                LatLng(address.latitude, address.longitude)
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun AddressMap(modifier: Modifier = Modifier) {
    var address by remember { mutableStateOf("") }
    var location by remember { mutableStateOf<LatLng?>(null) }
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()
    val coroutineScope = rememberCoroutineScope()
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { locationResult ->
            locationResult?.let {
                coroutineScope.launch {
                    location = LatLng(it.latitude, it.longitude)
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(location!!, 15f)
                }
            }
        }
    }

    LaunchedEffect(location) {
        location?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = address,
            onValueChange = { newAddress ->
                address = newAddress
                coroutineScope.launch {
                    location = geocodeAddress(context, newAddress)
                }
            },
            label = { Text("Enter Address") }
        )

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            cameraPositionState = cameraPositionState
        ) {
            location?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Location",
                    snippet = "Marker for searched address"
                )
            }
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCreateEventScreenLight(modifier: Modifier = Modifier) {
    NPreview {
        CreateEventContent()
    }
}
