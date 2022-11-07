package com.evgenykuksov.data.data.movies

import com.evgenykuksov.core.language.APP_LANGUAGE
import com.evgenykuksov.data.data.movies.local.memory.MoviesMemoryDataSource
import com.evgenykuksov.data.data.movies.remote.MoviesRemoteDataSource
import com.evgenykuksov.domain.movies.MoviesRepository
import com.evgenykuksov.domain.movies.model.*
import com.evgenykuksov.domain.movies.model.Credits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val memoryDataSource: MoviesMemoryDataSource
) : MoviesRepository {

    override fun getUpcoming(): Flow<List<Movie>> = flow {
        val cacheMemory = memoryDataSource.getUpcoming()
        if (cacheMemory.isNotEmpty()) {
            emit(cacheMemory)
        } else {
            remoteDataSource.getUpcoming()
                .map { it.results?.map { it.toDomain() }.orEmpty() }
                .first()
                .also { memoryDataSource.setUpcoming(it) }
                .also { emit(memoryDataSource.getUpcoming()) }
        }
    }
        .flowOn(Dispatchers.IO)

    override fun getNowPlaying(): Flow<List<Movie>> = flow {
        val cacheMemory = memoryDataSource.getNowPlaying()
        if (cacheMemory.isNotEmpty()) {
            emit(cacheMemory)
        } else {
            remoteDataSource.getNowPlaying()
                .map { it.results?.map { it.toDomain() }.orEmpty() }
                .first()
                .also { memoryDataSource.setNowPlaying(it) }
                .also { emit(memoryDataSource.getNowPlaying()) }
        }
    }
        .flowOn(Dispatchers.IO)

    override fun getPopular(): Flow<List<Movie>> = flow {
        val cacheMemory = memoryDataSource.getPopular()
        if (cacheMemory.isNotEmpty()) {
            emit(cacheMemory)
        } else {
            remoteDataSource.getPopular()
                .map { it.results?.map { it.toDomain() }.orEmpty() }
                .first()
                .also { memoryDataSource.setPopular(it) }
                .also { emit(memoryDataSource.getPopular()) }
        }
    }
        .flowOn(Dispatchers.IO)

    override fun getTopRated(): Flow<List<Movie>> = flow {
        val cacheMemory = memoryDataSource.getTopRated()
        if (cacheMemory.isNotEmpty()) {
            emit(cacheMemory)
        } else {
            remoteDataSource.getTopRated()
                .map { it.results?.map { it.toDomain() }.orEmpty() }
                .first()
                .also { memoryDataSource.setTopRated(it) }
                .also { emit(memoryDataSource.getTopRated()) }
        }
    }
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