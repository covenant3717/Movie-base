package com.evgenykuksov.data.data.movies

import com.evgenykuksov.data.data.movies.remote.MoviesRemoteStore
import com.evgenykuksov.domain.movies.MoviesRepository
import com.evgenykuksov.domain.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class MoviesRepositoryImpl(
    private val remoteStore: MoviesRemoteStore
) : MoviesRepository {

    override fun getNowPlaying(): Flow<List<Movie>> = remoteStore.getNowPlaying()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)

    override fun getPopular(): Flow<List<Movie>> = remoteStore.getPopular()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)

    override fun getTopRated(): Flow<List<Movie>> = remoteStore.getTopRated()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)
}