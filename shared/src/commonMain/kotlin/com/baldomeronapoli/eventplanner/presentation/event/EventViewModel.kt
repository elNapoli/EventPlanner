package com.baldomeronapoli.eventplanner.presentation.event

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.domain.usecases.events.CreateEventUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventByIdUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.GetEventsByAttendeeUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.events.SearchBoardGamesUseCase
import com.baldomeronapoli.eventplanner.domain.usecases.useCaseRunner
import com.baldomeronapoli.eventplanner.presentation.core.BaseViewModel
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.Effect
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiIntent
import com.baldomeronapoli.eventplanner.presentation.event.EventContract.UiState
import com.baldomeronapoli.eventplanner.presentation.models.AddressUI
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUIType
import com.rickclephas.kmp.observableviewmodel.launch
import dev.jordond.compass.Place
import dev.jordond.compass.autocomplete.Autocomplete
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult

class EventViewModel(
    private val createEventUseCase: CreateEventUseCase,
    private val geolocator: Geolocator,
    private val searchBoardGamesUseCase: SearchBoardGamesUseCase,
    private val getEventsByAttendeeUseCase: GetEventsByAttendeeUseCase,
    private val getEventByIdUseCase: GetEventByIdUseCase,

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

            UiIntent.LoadAllEventsByCurrentId -> fetchMyEvents()
            is UiIntent.GetEventById -> getEventById(uiIntent.eventId)
            is UiIntent.UpdateStartDateEvent -> updateUiState { copy(event = event.copy(startDate = uiIntent.value)) }
            is UiIntent.SetThumbnailByArray -> updateUiState { copy(tempThumbnail = uiIntent.file) }
        }
    }

    private fun getEventById(eventId: String) = scope.useCaseRunner(
        loadingUpdater = {
            updateUiState { copy(isLoading = it) }
        },
        onError = {},
        onSuccess = {
            updateUiState { copy(currentEvent = it?.mapToUI()) }
        },
        useCase = {
            getEventByIdUseCase(eventId = eventId)
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
                        message = it.message,
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
            createEventUseCase(uiState.value.event.toInstance(), uiState.value.tempThumbnail!!)
        }
    )

    private fun fetchMyEvents() = scope.useCaseRunner(
        loadingUpdater = {
            updateUiState { copy(isLoading = it) }
        },
        onError = {
            Logger.e(it.message)
            updateUiState {
                handleFeedbackUI(
                    feedbackUI = FeedbackUI(
                        title = "Error",
                        message = it.message,
                        type = FeedbackUIType.ERROR,
                        show = true
                    )
                )
            }
        },
        onSuccess = { events ->
            updateUiState { copy(ownEvents = events.map { it?.mapToUI() }) }
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
                            address = event.address.copy(
                                name = place.name,
                                street = place.street ?: "",
                                isoCountryCode = place.isoCountryCode,
                                country = place.country ?: "",
                                postalCode = place.postalCode,
                                administrativeArea = place.administrativeArea,
                                subAdministrativeArea = place.subAdministrativeArea,
                                locality = place.locality,
                                subLocality = place.subLocality,
                                thoroughfare = place.thoroughfare,
                                subThoroughfare = place.subThoroughfare,
                                latitude = place.coordinates.latitude,
                                longitude = place.coordinates.longitude

                            )
                        ),
                    )
                }
            } else {
                updateUiState {
                    copy(
                        event = event.copy(
                            address = AddressUI()
                        ),
                    )
                }
            }
        }
    }

    private fun searchBoardGamesByQuery(query: String) = scope.useCaseRunner(
        loadingUpdater = {},
        onError = {},
        onSuccess = {
            updateUiState { copy(boardGameBGG = it.map { game -> game?.mapToUI() }) }
        },
        useCase = {
            searchBoardGamesUseCase(query)
        }
    )

    private fun getCurrentLocation() {
        scope.launch {
            when (val result: GeolocatorResult = geolocator.current()) {
                is GeolocatorResult.Success -> {
                    val location = result.data
                    updateUiState {
                        copy(
                            event = event.copy(
                                address = event.address.copy(
                                    latitude = location.coordinates.latitude,
                                    longitude = location.coordinates.longitude,
                                )
                            ),
                        )
                    }
                }

                is GeolocatorResult.Error -> {}
            }
        }
    }

}
