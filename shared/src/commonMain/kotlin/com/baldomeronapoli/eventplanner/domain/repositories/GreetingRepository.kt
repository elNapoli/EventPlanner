package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GreetingRepository {
    @Throws(Exception::class)
    suspend fun getGreeting(): Flow<NetworkResult<String>>
}