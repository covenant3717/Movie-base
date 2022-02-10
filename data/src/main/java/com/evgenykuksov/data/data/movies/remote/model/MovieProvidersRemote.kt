package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieProvidersRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("results") val results: Map<String, MovieProviderDataRemote>
)