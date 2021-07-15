package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesDataRemote(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieRemote>?
)
