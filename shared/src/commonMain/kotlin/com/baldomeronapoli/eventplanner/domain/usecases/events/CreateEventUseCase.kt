package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class CreateEventUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(
        event: Event,
        games: List<BoardGame>,
        address: Address
    ): Flow<NetworkResult<Boolean>> =
        repository.createEvent(
            event = event,
            games = games,
            address = address
        )
}
