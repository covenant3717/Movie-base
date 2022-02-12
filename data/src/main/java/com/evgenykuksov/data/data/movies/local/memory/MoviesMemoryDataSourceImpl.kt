package com.evgenykuksov.data.data.movies.local.memory

import com.evgenykuksov.domain.movies.model.Movie

internal class MoviesMemoryDataSourceImpl() : MoviesMemoryDataSource {

    private var moviesUpcoming: List<Movie> = emptyList()
    private var moviesNowPlaying: List<Movie> = emptyList()
    private var moviesPopular: List<Movie> = emptyList()
    private var moviesTopRated: List<Movie> = emptyList()

    override suspend fun setUpcoming(list: List<Movie>) {
        moviesUpcoming = list
    }

    override suspend fun setNowPlaying(list: List<Movie>) {
        moviesNowPlaying = list
    }

    override suspend fun setPopular(list: List<Movie>) {
        moviesPopular = list
    }

    override suspend fun setTopRated(list: List<Movie>) {
        moviesTopRated = list
    }

    override suspend fun getUpcoming(): List<Movie> = moviesUpcoming

    override suspend fun getNowPlaying(): List<Movie> = moviesNowPlaying

    override suspend fun getPopular(): List<Movie> = moviesPopular

    override suspend fun getTopRated(): List<Movie> = moviesTopRated
}