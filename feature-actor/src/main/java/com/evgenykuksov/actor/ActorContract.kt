package com.evgenykuksov.actor

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState

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
    }
}