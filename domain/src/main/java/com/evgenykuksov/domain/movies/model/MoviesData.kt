package com.evgenykuksov.domain.movies.model

data class MoviesData(
    val listUpcoming: List<Movie>,
    val listNowPlaying: List<Movie>,
    val listPopular: List<Movie>,
    val listTopRated: List<Movie>
)