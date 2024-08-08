package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomero.napoli.eventplanner.core.data.utils.NetworkResult
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class CreateEventUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(
        event: Event,
        file: ByteArray
    ): Flow<NetworkResult<Boolean>> =
        repository.createEvent(
            event = event,
            file = file
        )
}
