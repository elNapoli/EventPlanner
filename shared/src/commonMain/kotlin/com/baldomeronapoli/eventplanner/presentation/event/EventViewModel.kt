package com.baldomeronapoli.eventplanner.presentation.event

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUI
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUIType
import com.baldomeronapoli.eventplanner.domain.models.NCoordinates
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventsByAttendeeUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.SearchBoardGamesUseCase
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
    private val searchBoardGamesUseCase: SearchBoardGamesUseCase,
    private val geolocator: Geolocator,
    private val getEventsByAttendeeUseCase: GetEventsByAttendeeUseCase,
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
            is UiIntent.UpdateQuery -> {
                updateUiState {
                    copy(queryGames = uiIntent.query)
                }
                if (uiIntent.query.length >= 3) {
                    searchBoardGamesByQuery(uiIntent.query)

                }
            }

            is UiIntent.AddGameIntoEvent -> updateUiState {
                copy(
                    event = event.copy(
                        boardgames = listOf(
                            uiIntent.game
                        )
                    )
                )
            }

            UiIntent.LoadAllEventsByCurrentId -> loadAllEventsByCurrentId()
        }
    }

    private fun searchBoardGamesByQuery(query: String) = scope.useCaseRunner(
        loadingUpdater = {},
        onError = {},
        onSuccess = {
            updateUiState { copy(boardGameBGG = it) }
        },
        useCase = {
            searchBoardGamesUseCase(query)
        }
    )

    private fun saveEvent() = scope.useCaseRunner(
        loadingUpdater = {
            updateUiState { copy(isLoading = it) }
        },
        onError = {
            updateUiState {
                handleFeedbackUI(
                    feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }
        },
        onSuccess = {
            updateUiState {
                handleFeedbackUI(
                    feedbackUI = FeedbackUI(
                        title = "Evento creado",
                        message = "Se ha creado el evento exitosamente!",
                        type = FeedbackUIType.SUCCESS,
                        show = true
                    )
                )
            }
        },
        useCase = {
            createEventUseCase(
                event = uiState.value.event,
                games = uiState.value.event.boardgames,
                address = uiState.value.event.place
            )
        }
    )

    private fun loadAllEventsByCurrentId() = scope.useCaseRunner(
        loadingUpdater = {
            updateUiState { copy(isLoading = it) }
        },
        onError = {
            updateUiState {
                handleFeedbackUI(
                    feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message ?: "Error desconocido",
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }
        },
        onSuccess = {
            updateUiState { copy(userEvents = it) }

        },
        useCase = {
            getEventsByAttendeeUseCase()
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
            Logger.e(place.toString())
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
                            place = Address()
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
