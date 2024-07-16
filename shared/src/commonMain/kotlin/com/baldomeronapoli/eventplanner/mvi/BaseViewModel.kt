package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface BaseUiSate
interface BaseUiAction
interface BaseSideEffect

abstract class BaseViewModel<STATE : BaseUiSate, ACTION : BaseUiAction, SIDEEFFECT : BaseSideEffect>(
    initialUiState: STATE
) : KmmViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: KmmStateFlow<STATE> = _uiState.asKmmStateFlow()

    private val _sideEffect = MutableSharedFlow<SIDEEFFECT>()
    val sideEffect: Flow<SIDEEFFECT> = _sideEffect.asKmmFlow()

    abstract fun onAction(uiAction: ACTION)

    fun updateUiState(block: STATE.() -> STATE) {
        _uiState.update(block)
    }

    protected fun emitSideEffect(sideEffect: SIDEEFFECT) {
        scope.launch {
            _sideEffect.emit(sideEffect)
        }
    }
}