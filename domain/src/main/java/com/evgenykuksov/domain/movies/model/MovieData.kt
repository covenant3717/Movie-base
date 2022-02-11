package com.evgenykuksov.domain.movies.model

data class MovieData(
    val details: MovieDetails,
    val cast: List<Actor>,
    val trailers: List<Trailer>,
    val images: MovieImages,
    val providers: List<MovieProvider>,
)
