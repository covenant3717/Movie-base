package com.evgenykuksov.domain.movies.model

sealed class MoviesCategory {
    object New : MoviesCategory()
    object Popular : MoviesCategory()
    object TopRated : MoviesCategory()
}