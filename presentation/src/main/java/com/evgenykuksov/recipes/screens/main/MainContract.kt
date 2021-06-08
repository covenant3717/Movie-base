package com.evgenykuksov.recipes.screens.main

import com.evgenykuksov.recipes.base.UiIntent
import com.evgenykuksov.recipes.base.UiSingleEvent
import com.evgenykuksov.recipes.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class MainContract {

    sealed class Intent : UiIntent {
        object Start : Intent()
    }

    sealed class State : UiState {
        object Idle : State()
        object Loading : State()
        data class Success(val list: List<Item>) : State()
    }

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}