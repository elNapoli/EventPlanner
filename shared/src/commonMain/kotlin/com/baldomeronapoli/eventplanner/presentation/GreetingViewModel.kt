package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.domain.usecases.GetGreetingUseCase
import com.baldomeronapoli.eventplanner.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GreetingViewModel(private val getGreetingUseCase: GetGreetingUseCase) : BaseViewModel() {

    private val _greet: MutableStateFlow<String> = MutableStateFlow("")

    val greet: StateFlow<String> get() = _greet

    init {
        getGreeting()
    }

    private fun getGreeting() {
        scope.launch {
            getGreetingUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success -> _greet.emit(result.data)
                    is NetworkResult.Error -> _greet.emit(result.exception)
                    is NetworkResult.Loading -> _greet.emit("cargando.....")
                }

            }

        }

    }
}