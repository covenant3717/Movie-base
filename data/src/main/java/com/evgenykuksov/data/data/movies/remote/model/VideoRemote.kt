package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class VideoRemote(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("key") val key: String?,
    @SerializedName("site") val site: String?,
    @SerializedName("official") val official: Boolean?,
)