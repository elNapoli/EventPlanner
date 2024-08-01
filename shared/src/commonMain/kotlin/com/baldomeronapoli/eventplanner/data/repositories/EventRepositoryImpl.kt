package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.data.managers.EventManager
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
    private val eventManager: EventManager
) : EventRepository {

    override suspend fun createEvent(
        event: Event,
        games: List<BoardGame>,
        address: Address
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading(true))
        emit(
            eventManager.createEvent(
                thumbnail = event.thumbnail!!,
                event = event.map(),
                games = games.map { it.map() },
                address = address.map()
            )
        )
    }

    override suspend fun getEventById(eventId: String): Flow<NetworkResult<Event?>> = flow {
        emit(NetworkResult.Loading(true))
        emit(
            eventManager.getEventById(
                eventId = eventId
            )
        )
    }

    override suspend fun searchBoardGames(query: String): Flow<NetworkResult<List<BoardGame>?>> =
        flow {
            emit(NetworkResult.Loading(true))
            try {
                val response = algoliaService.searchBoardGames(query)
                emit(
                    NetworkResult.Success(
                        response.data?.hits?.map { it.map() }
                    )
                )
            } catch (e: Throwable) {
                emit(NetworkResult.Error(exception = e, data = emptyList()))
            }
        }

    override suspend fun getEventsByAttendee(): Flow<NetworkResult<Triple<List<Event>, List<Event>, List<Event>>>> =
        flow {
            emit(NetworkResult.Loading(true))
            emit(eventManager.getEventsByAttendee())
        }
}