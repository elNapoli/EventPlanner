package com.baldomeronapoli.eventplanner.domain.usecases

import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetGreetingUseCase(
    private val repository: GreetingRepository,
) {
    suspend operator fun invoke(): Flow<NetworkResult<String>> = repository.getGreeting()
}
