package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieProviderDataRemote(
    @SerializedName("link") val link: String?,
    @SerializedName("flatrate") val flatrate: List<MovieProviderRemote>?,
    @SerializedName("rent") val rent: List<MovieProviderRemote>?,
    @SerializedName("buy") val buy: List<MovieProviderRemote>?,
)