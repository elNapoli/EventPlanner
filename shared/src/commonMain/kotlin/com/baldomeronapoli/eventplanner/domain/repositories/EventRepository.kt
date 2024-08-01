package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.domain.models.Address
import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.models.Event
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    @NativeCoroutines
    suspend fun createEvent(
        event: Event,
        games: List<BoardGame>,
        address: Address
    ): Flow<NetworkResult<Boolean>>

    @NativeCoroutines
    suspend fun getEventById(
        eventId: String
    ): Flow<NetworkResult<Event?>>

    @NativeCoroutines
    suspend fun searchBoardGames(query: String): Flow<NetworkResult<List<BoardGame>?>>

    @NativeCoroutines
    suspend fun getEventsByAttendee(): Flow<NetworkResult<Triple<List<Event>, List<Event>, List<Event>>>>

}