package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomero.napoli.common.network.NetworkResult
import com.baldomeronapoli.eventplanner.data.postgresql.queries.EventQueries
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val eventQueries: EventQueries
) : EventRepository {

    override suspend fun createEvent(
        event: Event,
        file: ByteArray
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))
        emit(NetworkResult.Success(eventQueries.saveEventInBD(event.mapToDto(), file)))
    }

    override suspend fun getEventById(eventId: String): Flow<NetworkResult<Event?>> = flow {
        emit(NetworkResult.Loading(true))


    }

    override suspend fun getEventsByAttendee(): Flow<NetworkResult<List<Event?>>> =
        flow {
            emit(NetworkResult.Loading(true))
            val events = eventQueries.getEventsByAttendee()
            emit(NetworkResult.Success(events.data.map {
                it.toInstance()

            }))
        }

    override suspend fun getNearbyEvents(
        page: Int,
        lat: Double,
        long: Double
    ): Flow<NetworkResult<List<Event?>>> = flow {
        emit(NetworkResult.Loading(true))
        val events = eventQueries.getNearbyEvents(page = page, lat = lat, lon = long)
        emit(NetworkResult.Success(events.data.map {
            it.toInstance()

        }))
    }

    override suspend fun searchBoardGames(query: String): Flow<NetworkResult<List<BoardGame?>>> =
        flow {
            emit(NetworkResult.Loading(true))
            val boardGamesDto = eventQueries.searchBoardGames(query)
            emit(NetworkResult.Success(boardGamesDto.map { it?.toInstance() }))
        }
}