package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomeronapoli.eventplanner.domain.models.BoardGame
import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class SearchBoardGamesUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(param: String): Flow<NetworkResult<List<BoardGame>?>> =
        repository.searchBoardGames(param)
}
