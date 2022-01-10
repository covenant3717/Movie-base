package com.evgenykuksov.movie

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class MovieContract {

    sealed class Intent : UiIntent {
        object Back: Intent()
    }

    data class State(
        val backdrop: String?,
        val name: String?,
        val date: String?,
        val listItems: List<Item>?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}