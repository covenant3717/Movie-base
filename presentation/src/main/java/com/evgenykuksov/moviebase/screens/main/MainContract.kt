package com.evgenykuksov.moviebase.screens.main

import com.evgenykuksov.moviebase.base.UiIntent
import com.evgenykuksov.moviebase.base.UiSingleEvent
import com.evgenykuksov.moviebase.base.UiState

class MainContract {

    sealed class Intent : UiIntent {
        object OverviewTouch : Intent()
        object BookmarksTouch : Intent()
    }

    data class State(
        val fragmentName: String,
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}