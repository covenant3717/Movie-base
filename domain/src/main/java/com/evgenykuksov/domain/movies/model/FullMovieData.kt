package com.evgenykuksov.domain.movies.model

data class FullMovieData(
    val movieDetails: MovieDetails,
    val listActor: List<Actor>
)
