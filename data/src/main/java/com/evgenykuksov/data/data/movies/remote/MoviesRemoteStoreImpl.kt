package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.api.MoviesApi
import kotlinx.coroutines.flow.flow

class MoviesRemoteStoreImpl(private val api: MoviesApi) : MoviesRemoteStore {

    override fun getRecipes() = flow { emit(api.getPopular()) }
}