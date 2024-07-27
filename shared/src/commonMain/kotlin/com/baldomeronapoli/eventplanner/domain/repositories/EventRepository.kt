package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import dev.gitlive.firebase.storage.File
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    @NativeCoroutines
    suspend fun createEvent(
        id: String,
        name: String,
        date: String,
        thumbnail: File,
        description: String,
        games: String,
        slots: Int,
        street: String,
        lat: Double,
        lon: Double,
    ): Flow<NetworkResult<Boolean>>
}