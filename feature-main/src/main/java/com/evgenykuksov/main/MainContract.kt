package com.evgenykuksov.main

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState

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