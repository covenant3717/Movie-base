package com.evgenykuksov.moviebase.screens.movie

import com.evgenykuksov.moviebase.base.UiIntent
import com.evgenykuksov.moviebase.base.UiSingleEvent
import com.evgenykuksov.moviebase.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class MovieContract {

    sealed class Intent : UiIntent {
        data class LoadMovieDetails(val movieId: Long) : Intent()
    }

    data class State(
        val poster: String?,
        val delayUpdateItems: Long,
        val listItems: List<Item>?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}