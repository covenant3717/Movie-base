package com.evgenykuksov.domain.movies.model

data class MovieData(
    val details: MovieDetails,
    val credits: Credits,
    val trailers: List<Trailer>,
    val images: MovieImages,
    val providers: List<MovieProvider>,
)
