package com.evgenykuksov.recipes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Intent : UiIntent, State : UiState, Effect : UiEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    val currentState: State
        get() = state.value

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _singleEffect: Channel<Effect> = Channel()
    val singleEffect = _singleEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }

    abstract fun createInitialState(): State

    abstract fun handleIntent(intent: Intent)

    fun setIntent(intent: Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _singleEffect.send(effectValue) }
    }

    protected fun launchOnThisScope(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }
}