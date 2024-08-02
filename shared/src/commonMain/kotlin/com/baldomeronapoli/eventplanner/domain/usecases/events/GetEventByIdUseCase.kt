package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow


class GetEventByIdUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(eventId: String): Flow<NetworkResult<Event?>> =
        repository.getEventById(eventId)
}