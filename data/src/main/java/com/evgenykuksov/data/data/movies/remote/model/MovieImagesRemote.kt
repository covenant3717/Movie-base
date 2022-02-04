package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieImagesRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("backdrops") val backdrops: List<ImageRemote>?,
    @SerializedName("posters") val posters: List<ImageRemote>?,
)