package com.evgenykuksov.actor

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class ActorContract {

    sealed class Intent : UiIntent {
    }

    data class State(
        val photo: String?,
        val listItems: List<Item>?,
        val delayUpdateItems: Long?,
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
        data class ShowDialogInfo(val listItems: List<Item>) : SingleEvent()
    }
}