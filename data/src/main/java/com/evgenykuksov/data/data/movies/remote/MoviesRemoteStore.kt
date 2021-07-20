package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteStore {

    fun getPopular(): Flow<MoviesDataRemote>

    fun getTopRated(): Flow<MoviesDataRemote>
}