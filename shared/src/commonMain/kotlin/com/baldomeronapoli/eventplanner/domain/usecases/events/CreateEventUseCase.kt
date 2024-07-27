package com.baldomeronapoli.eventplanner.domain.usecases.events

import com.baldomeronapoli.eventplanner.domain.repositories.EventRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.gitlive.firebase.storage.File
import kotlinx.coroutines.flow.Flow

class CreateEventUseCase(
    private val repository: EventRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(param: Param): Flow<NetworkResult<Boolean>> =
        repository.createEvent(
            param.id,
            param.title,
            param.date,
            param.thumbnail,
            param.description,
            param.games,
            param.slots,
            param.street,
            param.lat,
            param.lon
        )

    data class Param(
        val id: String,
        val title: String,
        val date: String,
        val thumbnail: File,
        val description: String,
        val games: String,
        val slots: Int,
        val street: String,
        val lat: Double,
        val lon: Double,
    )
}
