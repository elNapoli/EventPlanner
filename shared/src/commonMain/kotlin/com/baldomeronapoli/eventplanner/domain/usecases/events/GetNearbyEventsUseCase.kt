package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow


class GetNearbyEventsUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(
        page: Int,
        lat: Double,
        long: Double
    ): Flow<NetworkResult<List<Event?>>> =
        repository.getNearbyEvents(page = page, lat = lat, long = long)
}