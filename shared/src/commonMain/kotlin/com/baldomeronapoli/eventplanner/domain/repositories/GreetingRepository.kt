package com.baldomeronapoli.eventplanner.domain.repositories

import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GreetingRepository {
    suspend fun getGreeting(): Flow<NetworkResult<String>>
}