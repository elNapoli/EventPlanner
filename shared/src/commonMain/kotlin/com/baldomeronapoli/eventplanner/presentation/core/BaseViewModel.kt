package com.baldomeronapoli.eventplanner.presentation.core

import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.ViewModelScope
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface BaseUiSate
interface BaseUiIntent
interface BaseEffect

abstract class BaseViewModel<STATE : BaseUiSate, INTENT : BaseUiIntent, EFFECT : BaseEffect>(
    initialUiState: STATE
) : ViewModel() {

    val scope: ViewModelScope
        get() = viewModelScope

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<STATE> = _uiState

    private val _sideEffect = MutableSharedFlow<EFFECT>()
    val sideEffect: SharedFlow<EFFECT> = _sideEffect

    abstract fun handleIntent(uiIntent: INTENT)

    fun updateUiState(block: STATE.() -> STATE) {
        _uiState.update(block)
    }

    protected fun sendEffect(sideEffect: EFFECT) {
        scope.launch {
            _sideEffect.emit(sideEffect)
        }
    }
}