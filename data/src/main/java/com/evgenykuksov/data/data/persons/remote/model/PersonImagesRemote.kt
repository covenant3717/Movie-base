package com.evgenykuksov.data.data.persons.remote.model

import com.evgenykuksov.data.data.movies.remote.model.ImageRemote
import com.google.gson.annotations.SerializedName

internal data class PersonImagesRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("profiles") val profiles: List<ImageRemote>?
)
