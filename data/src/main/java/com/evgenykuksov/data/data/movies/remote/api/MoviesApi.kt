package com.evgenykuksov.data.data.movies.remote.api

import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import retrofit2.http.GET

internal interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun getNowPlaying(): MoviesDataRemote

    @GET("movie/popular")
    suspend fun getPopular(): MoviesDataRemote

    @GET("movie/top_rated")
    suspend fun getTopRated(): MoviesDataRemote
}