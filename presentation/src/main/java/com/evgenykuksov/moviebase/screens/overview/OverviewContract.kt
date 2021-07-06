package com.evgenykuksov.moviebase.screens.overview

import com.evgenykuksov.moviebase.base.UiIntent
import com.evgenykuksov.moviebase.base.UiSingleEvent
import com.evgenykuksov.moviebase.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class OverviewContract {

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