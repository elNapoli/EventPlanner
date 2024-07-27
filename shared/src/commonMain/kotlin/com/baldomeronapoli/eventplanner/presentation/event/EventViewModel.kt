package com.baldomeronapoli.eventplanner.presentation.event

import com.baldomeronapoli.eventplanner.domain.models.NCoordinates
import com.baldomeronapoli.eventplanner.domain.models.NPlace
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.rickclephas.kmp.observableviewmodel.launch
import dev.jordond.compass.Place
import dev.jordond.compass.autocomplete.Autocomplete
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult

class EventViewModel(
    private val createEventUseCase: CreateEventUseCase,
    private val geolocator: Geolocator
) : BaseViewModel<UiState, UiIntent, Effect>(UiState()) {

    init {
        getCurrentLocation()
    }

    override fun sendIntent(uiIntent: UiIntent) {
        when (uiIntent) {
            is UiIntent.SearchGeocode -> searchGeocode(uiIntent.address)
            is UiIntent.UpdateProperty -> updateUiState {
                validateProperties(uiIntent.nameProperty, uiIntent.value)
            }

            is UiIntent.UpdatePlace -> updatePlace(uiIntent.address)
            UiIntent.CreateEvent -> saveEvent()
            is UiIntent.SetThumbnail -> updateUiState { copy(event = event.copy(thumbnail = uiIntent.file)) }
        }
    }

    private fun saveEvent() = scope.useCaseRunner(
        loadingUpdater = {
            updateUiState { copy(isLoading = it) }
        },
        onError = {},
        onSuccess = {},
        useCase = {
            val param = CreateEventUseCase.Param(
                id = uiState.value.event.id,
                title = uiState.value.event.title,
                games = uiState.value.event.games,
                slots = uiState.value.event.slots ?: 0,
                date = uiState.value.event.date,
                description = uiState.value.event.description,
                lat = uiState.value.event.place.coordinates.latitude,
                lon = uiState.value.event.place.coordinates.longitude,
                thumbnail = uiState.value.event.thumbnail!!,
                street = uiState.value.event.place.street ?: ""
            )
            createEventUseCase(param)
        }
    )

    private fun updatePlace(address: String) {
        updateUiState { copy(queryAddress = address) }
        searchGeocode(address)
    }

    private fun searchGeocode(query: String) {
        scope.launch {
            val autocomplete = Autocomplete()
            val places: List<Place>? = autocomplete.search(query).getOrNull()
            val place = places?.firstOrNull()
            if (place != null) {
                updateUiState {
                    copy(
                        event = event.copy(
                            place = event.place.copy(
                                coordinates = NCoordinates(
                                    place.coordinates.latitude,
                                    place.coordinates.longitude
                                ),
                                name = place.name,
                                street = place.street,
                                isoCountryCode = place.isoCountryCode,
                                country = place.country,
                                postalCode = place.postalCode,
                                administrativeArea = place.administrativeArea,
                                subAdministrativeArea = place.subAdministrativeArea,
                                locality = place.locality,
                                subLocality = place.subLocality,
                                thoroughfare = place.thoroughfare,
                                subThoroughfare = place.subThoroughfare,
                            )
                        ),
                    )
                }
            } else {
                updateUiState {
                    copy(
                        event = event.copy(
                            place = NPlace()
                        ),
                    )
                }
            }
        }
    }

    private fun getCurrentLocation() {
        updateUiState { copy(isLoading = true) }
        scope.launch {
            when (val result: GeolocatorResult = geolocator.current()) {
                is GeolocatorResult.Success -> {
                    val location = result.data
                    val nCoordinates =
                        NCoordinates(location.coordinates.latitude, location.coordinates.longitude)
                    updateUiState {
                        copy(
                            event = event.copy(
                                place = event.place.copy(
                                    coordinates = nCoordinates
                                )
                            ),
                            isLoading = false
                        )
                    }
                }

                is GeolocatorResult.Error -> {}
            }
        }
    }

}
