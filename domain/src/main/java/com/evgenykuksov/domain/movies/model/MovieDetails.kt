package com.evgenykuksov.domain.movies.model

data class MovieDetails(
    val id: Long,
    val title: String,
    val backdropPath: String,
    val budget: Int,
    val revenue: Int,
    val genres: List<Genre>,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Float,
    val voteCount: Int
)