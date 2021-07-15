package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteStore {

    fun getRecipes(): Flow<MoviesDataRemote>
}