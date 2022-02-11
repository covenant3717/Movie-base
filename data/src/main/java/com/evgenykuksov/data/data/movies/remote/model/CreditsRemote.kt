package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class CreditsRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("cast") val cast: List<ActorRemote>?,
    @SerializedName("crew") val crew: List<WorkerRemote>?,
)