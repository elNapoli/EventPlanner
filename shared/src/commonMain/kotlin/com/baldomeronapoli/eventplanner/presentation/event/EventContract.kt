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
        var tempThumbnail: ByteArray? = null,
        var nextEvents: List<EventUI> = emptyList(),
        var expiredEvents: List<EventUI> = emptyList(),
        var nearbyEvents: List<EventUI?> = emptyList(),
        var ownEvents: List<EventUI?> = emptyList(),
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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as UiState

            if (event != other.event) return false
            if (queryAddress != other.queryAddress) return false
            if (tempThumbnail != null) {
                if (other.tempThumbnail == null) return false
                if (!tempThumbnail.contentEquals(other.tempThumbnail)) return false
            } else if (other.tempThumbnail != null) return false
            if (nextEvents != other.nextEvents) return false
            if (expiredEvents != other.expiredEvents) return false
            if (ownEvents != other.ownEvents) return false
            if (queryGames != other.queryGames) return false
            if (boardGameBGG != other.boardGameBGG) return false
            if (isLoading != other.isLoading) return false
            if (currentEvent != other.currentEvent) return false
            if (feedbackUI != other.feedbackUI) return false

            return true
        }

        override fun hashCode(): Int {
            var result = event.hashCode()
            result = 31 * result + queryAddress.hashCode()
            result = 31 * result + (tempThumbnail?.contentHashCode() ?: 0)
            result = 31 * result + nextEvents.hashCode()
            result = 31 * result + expiredEvents.hashCode()
            result = 31 * result + ownEvents.hashCode()
            result = 31 * result + queryGames.hashCode()
            result = 31 * result + boardGameBGG.hashCode()
            result = 31 * result + isLoading.hashCode()
            result = 31 * result + (currentEvent?.hashCode() ?: 0)
            result = 31 * result + (feedbackUI?.hashCode() ?: 0)
            return result
        }
    }

    sealed interface UiIntent : BaseUiIntent {
        data class SearchGeocode(val address: String) : UiIntent
        data class AddGameIntoEvent(val game: BoardGameUI) : UiIntent
        data class UpdateProperty(val nameProperty: String, val value: Any) : UiIntent

        data class UpdateStartDateEvent(val value: Long) : UiIntent
        data class UpdatePlace(val address: String) : UiIntent
        data class UpdateQuery(val query: String) : UiIntent

        data class SetThumbnailByArray(val file: ByteArray?) : UiIntent {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || this::class != other::class) return false

                other as SetThumbnailByArray

                if (file != null) {
                    if (other.file == null) return false
                    if (!file.contentEquals(other.file)) return false
                } else if (other.file != null) return false

                return true
            }

            override fun hashCode(): Int {
                return file?.contentHashCode() ?: 0
            }
        }

        data class GetEventById(val eventId: String) : UiIntent
        data object CreateEvent : UiIntent
        data object LoadAllEventsByCurrentId : UiIntent
        data class GetNearbyEvents(val page: Int) : UiIntent

    }

    sealed interface Effect : BaseEffect
}

