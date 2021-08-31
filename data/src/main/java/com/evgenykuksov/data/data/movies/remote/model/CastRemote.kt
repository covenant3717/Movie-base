package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class CastRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("cast") val cast: List<ActorRemote>?
)