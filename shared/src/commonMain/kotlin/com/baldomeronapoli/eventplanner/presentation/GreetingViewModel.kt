package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.mvi.KmmViewModel
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GreetingViewModel(private val getGreetingUseCase: GetGreetingUseCase) :
    KmmViewModel<GreetingState, GreetingEvent>(GreetingState.default()) {

    override fun observeEvents() {
        scope.launch {
            eventsFlow.collect { event ->
                when (event) {
                    is GreetingEvent.LoadData -> getGreeting()
                }
            }
        }
    }

    private fun getGreeting() {
        scope.launch {
            getGreetingUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            data = result.data
                        )
                    }

                    is NetworkResult.Loading -> _state.update { it.copy(isLoading = true) }
                    is NetworkResult.Error -> handleException(result.exception)
                }

            }

        }

    }

    private fun handleException(e: String) {
        _state.update { it.copy(isLoading = false, errorMessage = e) }
    }
}