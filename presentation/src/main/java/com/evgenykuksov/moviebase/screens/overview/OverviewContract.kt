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

    data class State(val state: OverviewState) : UiState

    sealed class OverviewState : UiState {
        object Idle : OverviewState()
        data class Loading(val listLoadingItems: List<Item>) : OverviewState()
        data class Success(val listItems: List<Item>) : OverviewState()
        data class Error(val listErrorItems: List<Item>) : OverviewState()
    }

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}