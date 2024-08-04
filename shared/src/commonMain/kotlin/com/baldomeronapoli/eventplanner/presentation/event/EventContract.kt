package com.baldomeronapoli.eventplanner.presentation.event

import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import com.baldomeronapoli.eventplanner.presentation.models.BoardGameUI
import com.baldomeronapoli.eventplanner.presentation.models.EventUI
import com.baldomeronapoli.eventplanner.presentation.models.FeedbackUI

interface EventContract {
    data class UiState(
        var event: EventUI = EventUI(),
        var queryAddress: String = "",
        var nextEvents: List<EventUI> = emptyList(),
        var expiredEvents: List<EventUI> = emptyList(),
        var ownEvents: List<EventUI> = emptyList(),
        var queryGames: String = "",
        var boardGameBGG: List<BoardGameUI?> = emptyList(),
        var isLoading: Boolean = false,
        var currentEvent: EventUI? = null,
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
        data class AddGameIntoEvent(val game: BoardGameUI) : UiIntent
        data class UpdateProperty(val nameProperty: String, val value: Any) : UiIntent

        data class UpdateStartDateEvent(val value: Long) : UiIntent
        data class UpdatePlace(val address: String) : UiIntent
        data class UpdateQuery(val query: String) : UiIntent

        // data class SetThumbnail(val file: File) : UiIntent
        data class GetEventById(val eventId: String) : UiIntent
        data object CreateEvent : UiIntent
        data object LoadAllEventsByCurrentId : UiIntent

    }

    sealed interface Effect : BaseEffect
}

