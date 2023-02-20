package com.evgenykuksov.home

import com.evgenykuksov.domain.movies.model.MoviesCategory
import com.evgenykuksov.core.base.UiIntent
import com.evgenykuksov.core.base.UiSingleEvent
import com.evgenykuksov.core.base.UiState
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MoviesGrouping

internal class HomeContract {

    sealed class Intent : UiIntent {
        data class ChangeGrouping(val grouping: MoviesGrouping) : Intent()
        data class SelectCategory(val category: MoviesCategory) : Intent()
    }

    data class State(
        val grouping: MoviesGrouping,
        val category: MoviesCategory,
        val movies: List<Movie>,
        val rating: Int?
    ) : UiState

    sealed class SingleEvent : UiSingleEvent {
        data class ToastError(val message: String) : SingleEvent()
    }
}