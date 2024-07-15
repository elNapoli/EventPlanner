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

abstract class BaseViewModel(initialUiState: BaseUiSate) :
    KmmViewModel<BaseUiSate, BaseUiAction, BaseSideEffect>(initialUiState) {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<BaseUiSate> = _uiState.asStateFlow()

    private val _sideEffect by lazy { Channel<BaseSideEffect>() }
    val sideEffect: Flow<BaseSideEffect> by lazy { _sideEffect.receiveAsFlow() }

    abstract fun onAction(uiAction: BaseUiAction)

    fun updateUiState(newUiState: BaseUiSate) {
        _uiState.update { newUiState }
    }

    fun updateUiState(block: BaseUiSate.() -> BaseUiSate) {
        _uiState.update(block)
    }

    fun CoroutineScope.emitSideEffect(effect: BaseSideEffect) {
        this.launch { _sideEffect.send(effect) }
    }
}