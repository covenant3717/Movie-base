package com.evgenykuksov.actor

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

internal class ActorContract {

    sealed class Intent : UiIntent {
        object InfoClicked: Intent()
    }

    data class State(
        val listPhotos: List<String?>?,
        val pagerEndPaddingRes: Int?,
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
        data class ShowDialogInfo(val listItems: List<Item>) : SingleEvent()
    }
}