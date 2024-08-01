package com.baldomeronapoli.eventplanner.presentation.event

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.storage.File

interface EventContract {
    data class UiState(
        var event: Event = Event(),
        var queryAddress: String = "",
        var nextEvents: List<Event> = emptyList(),
        var expiredEvents: List<Event> = emptyList(),
        var ownEvents: List<Event> = emptyList(),
        var queryGames: String = "",
        var boardGameBGG: List<BoardGame>? = emptyList(),
        var isLoading: Boolean = false,
        var currentEvent: Event? = null,
        var feedbackUI: FeedbackUI? = null

    ) : BaseUiState() {

        fun handleFeedbackUI(
            feedbackUI: FeedbackUI?
        ): UiState =
            copy(feedbackUI = feedbackUI)

        fun validateProperties(propertyName: String, value: Any): UiState {
            return when (propertyName) {
                "title" -> copy(event = event.copy(title = value.toString()))
                "slots" -> {
                    copy(event = event.copy(slots = value.toString().toInt()))
                }

                "description" -> copy(event = event.copy(description = value.toString()))
                else -> this
            }
        }
    }

    sealed interface UiIntent : BaseUiIntent {
        data class SearchGeocode(val address: String) : UiIntent
        data class AddGameIntoEvent(val game: BoardGame) : UiIntent
        data class UpdateProperty(val nameProperty: String, val value: Any) : UiIntent
        data class UpdateDateEvent(val value: Timestamp) : UiIntent
        data class UpdatePlace(val address: String) : UiIntent
        data class UpdateQuery(val query: String) : UiIntent
        data class SetThumbnail(val file: File) : UiIntent
        data class GetEventById(val eventId: String) : UiIntent
        data object CreateEvent : UiIntent
        data object LoadAllEventsByCurrentId : UiIntent

    }

    sealed interface Effect : BaseEffect
}

