package com.evgenykuksov.domain.movies.model

enum class MoviesCategory(val position: Int) {
    UPCOMING(0),
    NOW_PLAYING(1),
    POPULAR(2),
    TOP_RATED(3);
}