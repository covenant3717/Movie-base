package com.evgenykuksov.data.data.movies.remote

import kotlinx.coroutines.flow.flow

internal class MoviesRemoteDataSourceImpl(private val api: MoviesApi) : MoviesRemoteDataSource {

    override fun getNowPlaying() = flow { emit(api.getNowPlaying()) }

    override fun getPopular() = flow { emit(api.getPopular()) }

    override fun getTopRated() = flow { emit(api.getTopRated()) }

    override fun getDetails(movieId: Long) = flow { emit(api.getDetails(movieId)) }

    override fun getCast(movieId: Long) = flow { emit(api.getCast(movieId)) }

    override fun getTrailers(movieId: Long) = flow { emit(api.getTrailers(movieId)) }
}