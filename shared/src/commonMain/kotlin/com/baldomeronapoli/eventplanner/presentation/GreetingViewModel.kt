package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.mvi.KmmViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GreetingViewModel(private val getGreetingUseCase: GetGreetingUseCase) :
    KmmViewModel<GreetingState, GreetingEvent>(GreetingState.default()) {

    override fun observeEvents() {
        scope.launch {
            eventsFlow.collect { event ->
                when (event) {
                    is GreetingEvent.LoadData -> getGreeting()
                    is GreetingEvent.OtroEvento -> {

                    }
                }
            }
        }
    }

    private fun getGreeting() = withUseCaseScope(
        loadingUpdater = { value -> _state.update { it.copy(isLoading = value) } },
        onError = { handleException(it.message.toString()) },
        onSuccess = { data -> _state.update { it.copy(data = data) } },
        useCase = { getGreetingUseCase() }
    )

    private fun handleException(e: String) {
        _state.update { it.copy(isLoading = false, errorMessage = e) }
    }
}