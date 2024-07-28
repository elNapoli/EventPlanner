package com.baldomeronapoli.eventplanner.presentation.event

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.models.FeedbackUI
import com.baldomeronapoli.eventplanner.presentation.auth.AuthContract.UiState
import com.baldomeronapoli.eventplanner.presentation.core.BaseEffect
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiIntent
import com.baldomeronapoli.eventplanner.presentation.core.BaseUiState
import dev.gitlive.firebase.storage.File

interface EventContract {
    data class UiState(
        var event: Event = Event(),
        var queryAddress: String = "",
        var queryGames: String = "",
        var games: List<BoardGame>? = emptyList(),
        var isLoading: Boolean = false,
        var feedbackUI: FeedbackUI? = null

    ) : BaseUiState() {

        fun handleFeedbackUI(
            feedbackUI: FeedbackUI?
        ): UiState =
            copy(feedbackUI = feedbackUI)

        fun validateProperties(propertyName: String, value: Any?): UiState {
            return when (propertyName) {
                "title" -> copy(event = event.copy(title = value.toString()))
                "games" -> copy(event = event.copy(games = value.toString()))
                "slots" -> {
                    val slotValue = (value.toString()).takeIf { it.isNotEmpty() }?.toIntOrNull()
                    copy(event = event.copy(slots = slotValue))
                }

                "date" -> copy(event = event.copy(date = value.toString()))
                "description" -> copy(event = event.copy(description = value.toString()))
                else -> this
            }
        }
    }

    sealed interface UiIntent : BaseUiIntent {
        data class SearchGeocode(val address: String) : UiIntent
        data class UpdateProperty(val nameProperty: String, val value: Any?) : UiIntent
        data class UpdatePlace(val address: String) : UiIntent
        data class UpdateQuery(val query: String) : UiIntent
        data class SetThumbnail(val file: File) : UiIntent
        data object CreateEvent : UiIntent

    }

    sealed interface Effect : BaseEffect
}

