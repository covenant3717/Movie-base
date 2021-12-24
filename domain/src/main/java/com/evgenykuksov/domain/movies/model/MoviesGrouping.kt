package com.evgenykuksov.domain.movies.model

sealed class MoviesGrouping {
    object Linear: MoviesGrouping()
    object Grid: MoviesGrouping()
}
