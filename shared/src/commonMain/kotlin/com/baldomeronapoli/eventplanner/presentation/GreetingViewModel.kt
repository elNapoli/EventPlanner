package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.mvi.KmmStateFlow
import com.baldomeronapoli.eventplanner.mvi.KmmViewModel
import com.baldomeronapoli.eventplanner.mvi.asKmmStateFlow
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GreetingViewModel(private val getGreetingUseCase: GetGreetingUseCase) : KmmViewModel() {

    private val _state = MutableStateFlow(GreetingState.default())
    val state: KmmStateFlow<GreetingState> get() = _state.asKmmStateFlow()

    init {
        getGreeting()
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