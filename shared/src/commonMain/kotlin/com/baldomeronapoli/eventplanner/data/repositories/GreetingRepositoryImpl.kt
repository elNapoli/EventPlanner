package com.baldomeronapoli.eventplanner.data.repositories

import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GreetingRepositoryImpl : GreetingRepository {
    override suspend fun getGreeting(): Flow<NetworkResult<String>> = flow {
        emit(NetworkResult.Loading(true))
        delay(4000)
        emit(NetworkResult.Success("esto es una prueba y estoy viendo que hcucha pasa"))
    }
}