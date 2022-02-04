package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.*
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>

    fun getDetails(movieId: Long): Flow<MovieDetails>

    fun getCast(movieId: Long): Flow<List<Actor>>

    fun getTrailers(movieId: Long): Flow<List<Trailer>>

    fun getImages(movieId: Long): Flow<MovieImages>
}