package com.evgenykuksov.data.data.movies.local.memory

import com.evgenykuksov.domain.movies.model.Movie

internal interface MoviesMemoryDataSource {

    suspend fun setUpcoming(list: List<Movie>)

    suspend fun setNowPlaying(list: List<Movie>)

    suspend fun setPopular(list: List<Movie>)

    suspend fun setTopRated(list: List<Movie>)

    suspend fun getUpcoming(): List<Movie>

    suspend fun getNowPlaying(): List<Movie>

    suspend fun getPopular(): List<Movie>

    suspend fun getTopRated(): List<Movie>
}