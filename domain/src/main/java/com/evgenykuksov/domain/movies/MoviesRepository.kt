package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>

    fun getMovieDetails(id: Long): Flow<MovieDetails>
}