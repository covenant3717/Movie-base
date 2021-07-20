package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.api.MoviesApi
import kotlinx.coroutines.flow.flow

class MoviesRemoteStoreImpl(private val api: MoviesApi) : MoviesRemoteStore {

    override fun getPopular() = flow { emit(api.getPopular()) }

    override fun getTopRated() = flow { emit(api.getTopRated()) }
}