package com.evgenykuksov.movie

import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

internal class MovieContract {

    sealed class Intent : UiIntent {
        object Back: Intent()
    }

    data class State(
        val backdrop: String?,
        val name: String?,
        val date: String?,
        val duration: Int?,
        val delayUpdateItems: Long?,
        val listBackdrops: List<Item>?,
        val listItems: List<Item>?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}