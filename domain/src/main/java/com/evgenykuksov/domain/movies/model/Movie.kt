package com.evgenykuksov.domain.movies.model

data class Movie(
    val id: Long,
    val backdropPath: String,
    val genreIds: List<Int>,
    val title: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: Int
)