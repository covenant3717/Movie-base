package com.evgenykuksov.data.data.movies

import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSource
import com.evgenykuksov.domain.movies.MoviesRepository
import com.evgenykuksov.domain.movies.model.Actor
import com.evgenykuksov.domain.movies.model.Movie
import com.evgenykuksov.domain.movies.model.MovieDetails
import com.evgenykuksov.domain.movies.model.MovieVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class MoviesRepositoryImpl(private val remoteDataSource: MoviesRemoteDataSource) : MoviesRepository {

    override fun getNowPlaying(): Flow<List<Movie>> = remoteDataSource.getNowPlaying()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)

    override fun getPopular(): Flow<List<Movie>> = remoteDataSource.getPopular()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)

    override fun getTopRated(): Flow<List<Movie>> = remoteDataSource.getTopRated()
        .map { it.results?.map { it.toDomain() }.orEmpty() }
        .flowOn(Dispatchers.IO)

    override fun getDetails(movieId: Long): Flow<MovieDetails> = remoteDataSource.getDetails(movieId)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override fun getCast(movieId: Long): Flow<List<Actor>> = remoteDataSource.getCast(movieId)
        .map { it.cast.orEmpty() }
        .map { it.map { actorRemote -> actorRemote.toDomain() } }
        .flowOn(Dispatchers.IO)

    override fun getVideos(movieId: Long): Flow<List<MovieVideo>> = remoteDataSource.getVideos(movieId)
        .map { it.results.map { it.toDomain() } }
        .flowOn(Dispatchers.IO)
}