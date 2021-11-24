package com.evgenykuksov.movie

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class MovieContract {

    sealed class Intent : UiIntent {
        data class LoadMovieDetails(val movieId: Long) : Intent()
    }

    data class State(
        val backdrop: String?,
        val name: String?,
        val delayUpdateItems: Long,
        val listItems: List<Item>?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
        data class StartActorActivity(val actorId: Long) : SingleEvent()
    }
}