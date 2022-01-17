package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MovieDetails
import com.evgenykuksov.domain.movies.model.Trailer
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>

    fun getDetails(movieId: Long): Flow<MovieDetails>

    fun getCast(movieId: Long): Flow<List<Actor>>

    fun getTrailers(movieId: Long): Flow<List<Trailer>>
}