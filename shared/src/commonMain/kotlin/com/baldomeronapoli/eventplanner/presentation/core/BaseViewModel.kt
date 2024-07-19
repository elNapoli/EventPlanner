package com.baldomeronapoli.eventplanner.presentation.core

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.ViewModelScope
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


interface BaseUiSate
interface BaseUiIntent
interface BaseEffect
abstract class BaseViewModel<STATE : BaseUiSate, INTENT : BaseUiIntent, EFFECT : BaseEffect>(
    initialUiState: STATE
) : ViewModel() {

    val scope: ViewModelScope
        get() = viewModelScope

    private val _uiState = MutableStateFlow(viewModelScope, initialUiState)

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<EFFECT?>(viewModelScope, null)

    @NativeCoroutinesState
    val effect = _effect.asStateFlow()

    abstract fun handleIntent(uiIntent: INTENT)

    fun updateUiState(block: STATE.() -> STATE) {
        _uiState.update(block)
    }

    protected fun sendEffect(effect: EFFECT) {
        scope.launch {
            _effect.emit(effect)
        }
    }
}