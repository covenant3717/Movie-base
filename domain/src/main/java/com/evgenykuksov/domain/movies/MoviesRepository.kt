package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>
}