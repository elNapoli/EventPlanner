package com.baldomeronapoli.eventplanner.domain.usecases

import com.baldomeronapoli.eventplanner.domain.repositories.GreetingRepository
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

class GetGreetingUseCase(
    private val repository: GreetingRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(): Flow<NetworkResult<String>> = repository.getGreeting()
}
