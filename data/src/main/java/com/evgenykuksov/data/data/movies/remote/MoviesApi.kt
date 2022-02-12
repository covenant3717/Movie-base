package com.evgenykuksov.data.data.movies.remote

import com.evgenykuksov.data.data.movies.remote.model.CreditsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieDetailsRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieImagesRemote
import com.evgenykuksov.data.data.movies.remote.model.MoviesDataRemote
import com.evgenykuksov.data.data.movies.remote.model.MovieProvidersRemote
import com.evgenykuksov.data.data.movies.remote.model.TrailersRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesApi {

    @GET("movie/upcoming")
    suspend fun getNowPlaying(): MoviesDataRemote

    @GET("movie/popular")
    suspend fun getPopular(): MoviesDataRemote

    @GET("movie/top_rated")
    suspend fun getTopRated(): MoviesDataRemote

    @GET("movie/{movie_id}")
    suspend fun getDetails(@Path("movie_id") id: Long): MovieDetailsRemote

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") id: Long): CreditsRemote

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailers(@Path("movie_id") id: Long): TrailersRemote

    @GET("movie/{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") id: Long,
        @Query("language") language: String = "null"
    ): MovieImagesRemote

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getProviders(@Path("movie_id") id: Long): MovieProvidersRemote
}