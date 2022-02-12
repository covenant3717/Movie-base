package com.evgenykuksov.data.data.movies

import com.evgenykuksov.core.language.APP_LANGUAGE
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSource
import com.evgenykuksov.domain.movies.MoviesRepository
import com.evgenykuksov.domain.movies.model.*
import com.evgenykuksov.domain.movies.model.Credits
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

    override fun getCredits(movieId: Long): Flow<Credits> = remoteDataSource.getCredits(movieId)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override fun getTrailers(movieId: Long): Flow<List<Trailer>> = remoteDataSource.getTrailers(movieId)
        .map { it.results.map { it.toDomain() } }
        .flowOn(Dispatchers.IO)

    override fun getImages(movieId: Long): Flow<MovieImages> = remoteDataSource.getImages(movieId)
        .map { it.toDomain() }
        .flowOn(Dispatchers.IO)

    override fun getProviders(movieId: Long): Flow<List<MovieProvider>> = remoteDataSource.getProviders(movieId)
        .map { it.results.getOrDefault(APP_LANGUAGE.uppercase(), null) }
        .map { it?.toDomain() ?: emptyList() }
        .map { it.distinctBy { it.id } }
        .flowOn(Dispatchers.IO)
}