package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface BaseUiSate
interface BaseUiAction
interface BaseSideEffect

abstract class BaseViewModel<STATE : BaseUiSate, ACTION : BaseUiAction, SIDEEFFECT : BaseSideEffect>(
    initialUiState: STATE
) : KmmViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _sideEffect by lazy { Channel<SIDEEFFECT>() }
    val sideEffect: Flow<SIDEEFFECT> by lazy { _sideEffect.receiveAsFlow() }

    abstract fun onAction(uiAction: ACTION)

    fun updateUiState(block: STATE.() -> STATE) {
        _uiState.update(block)
    }

    fun CoroutineScope.emitSideEffect(effect: SIDEEFFECT) {
        this.launch { _sideEffect.send(effect) }
    }
}