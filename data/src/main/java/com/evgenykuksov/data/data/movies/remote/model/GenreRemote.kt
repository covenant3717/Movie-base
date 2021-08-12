package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class GenreRemote(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?
)