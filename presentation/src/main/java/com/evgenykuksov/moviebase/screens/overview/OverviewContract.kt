package com.evgenykuksov.moviebase.screens.overview

import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.moviebase.base.UiIntent
import com.evgenykuksov.moviebase.base.UiSingleEvent
import com.evgenykuksov.moviebase.base.UiState
import com.xwray.groupie.kotlinandroidextensions.Item

class OverviewContract {

    sealed class Intent : UiIntent {
        object Start : Intent()
        data class SelectCategory(val category: MoviesCategory) : Intent()
    }

    data class State(
        val listItems: List<Item>?,
        val rating: Int?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}