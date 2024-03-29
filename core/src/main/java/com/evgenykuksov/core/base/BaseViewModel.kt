package com.evgenykuksov.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgenykuksov.core.items.ErrorItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Intent : UiIntent, State : UiState, SingleEvent : UiSingleEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    private val intent = _intent.asSharedFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _singleEvent: Channel<SingleEvent> = Channel()
    val singleEvent = _singleEvent.receiveAsFlow()

    init {
        subscribeEvents()
    }

    abstract fun createInitialState(): State

    abstract fun handleIntent(intent: Intent)

    private fun subscribeEvents() = viewModelScope.launch {
        intent.collect {
            handleIntent(it)
        }
    }

    fun sendIntent(intent: Intent) = viewModelScope.launch { _intent.emit(intent) }

    protected fun setState(reduce: State.() -> State) {
        val currentState = state.value
        val newState = currentState.reduce()
        _state.value = newState
    }

    protected fun setSingleEvent(effect: SingleEvent) = viewModelScope.launch { _singleEvent.send(effect) }

    protected fun buildErrorItems() = listOf<Item>(ErrorItem())
}