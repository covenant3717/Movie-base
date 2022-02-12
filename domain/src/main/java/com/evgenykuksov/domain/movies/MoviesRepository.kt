package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.*
import com.evgenykuksov.domain.movies.model.Credits
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getUpcoming(): Flow<List<Movie>>

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>

    fun getDetails(movieId: Long): Flow<MovieDetails>

    fun getCredits(movieId: Long): Flow<Credits>

    fun getTrailers(movieId: Long): Flow<List<Trailer>>

    fun getImages(movieId: Long): Flow<MovieImages>

    fun getProviders(movieId: Long): Flow<List<MovieProvider>>
}