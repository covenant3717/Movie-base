package com.evgenykuksov.data.data.movies

import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStore
import com.evgenykuksov.domain.recipes.MoviesRepository
import com.evgenykuksov.domain.recipes.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val remoteStore: MoviesRemoteStore
) : MoviesRepository {

    override fun getRecipes(): Flow<List<Movie>> = remoteStore.getRecipes()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)
}