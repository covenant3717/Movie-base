package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.api.MoviesApi
import kotlinx.coroutines.flow.flow

internal class MoviesRemoteStoreImpl(private val api: MoviesApi) : MoviesRemoteStore {

    override fun getNowPlaying() = flow { emit(api.getNowPlaying()) }

    override fun getPopular() = flow { emit(api.getPopular()) }

    override fun getTopRated() = flow { emit(api.getTopRated()) }
}