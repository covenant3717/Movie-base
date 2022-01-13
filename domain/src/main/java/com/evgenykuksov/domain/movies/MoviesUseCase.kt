package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.MovieData
import com.evgenykuksov.domain.movies.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun getMovies(): Flow<MoviesData>

    fun getMovieData(movieId: Long): Flow<MovieData>
}