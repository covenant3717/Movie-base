package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MoviesData
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun getAll(): Flow<MoviesData>

    fun getNowPlaying(): Flow<List<Movie>>

    fun getPopular(): Flow<List<Movie>>

    fun getTopRated(): Flow<List<Movie>>
}