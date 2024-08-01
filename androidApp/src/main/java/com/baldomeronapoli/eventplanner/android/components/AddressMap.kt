package com.baldomeronapoli.eventplanner.android.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.eventplanner.android.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun AddressMap(
    modifier: Modifier = Modifier,
    address: String,
    lat: Double,
    lng: Double,
    previewMode: Boolean = false,
    onChange: (String) -> Unit
) {
    val location = LatLng(lat, lng)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    LaunchedEffect(location) {
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(location, 15f)
        )
    }

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (previewMode) {
            Text(text = address)
        } else {
            NOutlinedTextField(
                value = address,
                maxLines = 1,
                label = stringResource(id = R.string.event_address),
                onValueChange = { onChange(it) }
            )
        }


        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = location),
                title = stringResource(id = R.string.my_events),
                snippet = stringResource(id = R.string.marker_for_searched_address)
            )
        }
    }
}
