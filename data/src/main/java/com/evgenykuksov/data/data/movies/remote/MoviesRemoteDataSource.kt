package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.model.CreditsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieImagesRemote
import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieProvidersRemote
import com.evgenykuksov.data.data.movies.remote.model.TrailersRemote
import kotlinx.coroutines.flow.Flow

internal interface MoviesRemoteDataSource {

    fun getNowPlaying(): Flow<MoviesDataRemote>

    fun getPopular(): Flow<MoviesDataRemote>

    fun getTopRated(): Flow<MoviesDataRemote>

    fun getDetails(movieId: Long): Flow<MovieDetailsRemote>

    fun getCredits(movieId: Long): Flow<CreditsRemote>

    fun getTrailers(movieId: Long): Flow<TrailersRemote>

    fun getImages(movieId: Long): Flow<MovieImagesRemote>

    fun getProviders(movieId: Long): Flow<MovieProvidersRemote>
}