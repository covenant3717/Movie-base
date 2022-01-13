package com.evgenykuksov.domain.movies.model

data class MovieData(
    val details: MovieDetails,
    val cast: List<Actor>,
    val videos: List<MovieVideo>,
)
