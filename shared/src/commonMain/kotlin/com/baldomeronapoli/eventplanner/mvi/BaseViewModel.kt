package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val uiState2: StateFlow<STATE> = _uiState.asStateFlow()
    // FIXME: hay un bug con uiState, por alguna extraña razon el viewModel no tiene acceso a los cambios
    //  que se hacen en el uiState. tiene que ver con la arquitectura KMM y el flujo de datos. es IMPORTANTE QUE SE RESULEVA

    private val _sideEffect = MutableSharedFlow<SIDEEFFECT>()
    val sideEffect: KmmFlow<SIDEEFFECT> = _sideEffect.asKmmFlow()

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