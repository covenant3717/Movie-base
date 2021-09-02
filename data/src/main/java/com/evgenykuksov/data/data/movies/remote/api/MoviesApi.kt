package com.evgenykuksov.data.data.movies.remote.api

import com.evgenykuksov.data.data.movies.remote.model.CastRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun getNowPlaying(): MoviesDataRemote

    @GET("movie/popular")
    suspend fun getPopular(): MoviesDataRemote

    @GET("movie/top_rated")
    suspend fun getTopRated(): MoviesDataRemote

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Long): MovieDetailsRemote

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(@Path("movie_id") id: Long): CastRemote
}