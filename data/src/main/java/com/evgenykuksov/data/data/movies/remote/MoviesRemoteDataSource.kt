package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.model.CastRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import com.evgenykuksov.data.data.movies.remote.model.VideosRemote
import kotlinx.coroutines.flow.Flow

internal interface MoviesRemoteDataSource {

    fun getNowPlaying(): Flow<MoviesDataRemote>

    fun getPopular(): Flow<MoviesDataRemote>

    fun getTopRated(): Flow<MoviesDataRemote>

    fun getDetails(movieId: Long): Flow<MovieDetailsRemote>

    fun getCast(movieId: Long): Flow<CastRemote>

    fun getVideos(movieId: Long): Flow<VideosRemote>
}