package com.baldomeronapoli.eventplanner.data.repositories

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.data.postgresql.queries.EventQueries
import com.baldomeronapoli.eventplanner.data.services.AlgoliaService
import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val algoliaService: AlgoliaService,
    private val eventQueries: EventQueries
) : EventRepository {

    override suspend fun createEvent(
        event: Event,
        games: List<BoardGame>,
        address: Address
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))

    }

    override suspend fun getEventById(eventId: String): Flow<NetworkResult<Event?>> = flow {
        emit(NetworkResult.Loading(true))


    }

    override suspend fun getEventsByAttendee(): Flow<NetworkResult<List<Event?>>> =
        flow {
            emit(NetworkResult.Loading(true))
            val events = eventQueries.getEventsByAttendee(1)

            Logger.e(events.toString())
        }
}