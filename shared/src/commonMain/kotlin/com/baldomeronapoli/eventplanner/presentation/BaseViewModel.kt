package com.baldomeronapoli.eventplanner.presentation

import com.baldomeronapoli.eventplanner.utils.KmmViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface BaseUiSate
interface BaseUiIntent
interface BaseEffect

abstract class BaseViewModel<STATE : BaseUiSate, INTENT : BaseUiIntent, EFFECT : BaseEffect>(
    initialUiState: STATE
) : KmmViewModel() {

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