package com.evgenykuksov.domain.movies

import com.evgenykuksov.domain.movies.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopular(): Flow<List<Movie>>
}