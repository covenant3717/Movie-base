package com.evgenykuksov.moviebase.screens.actor

import com.evgenykuksov.moviebase.base.UiIntent
import com.evgenykuksov.moviebase.base.UiSingleEvent
import com.evgenykuksov.moviebase.base.UiState

class ActorContract {

    sealed class Intent : UiIntent {
        data class LoadActorDetails(val actorId: Long) : Intent()
    }

    data class State(
        val photo: String?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}