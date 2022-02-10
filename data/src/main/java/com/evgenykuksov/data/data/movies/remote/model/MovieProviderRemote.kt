package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieProviderRemote(
    @SerializedName("provider_id") val providerId: Int,
    @SerializedName("provider_name") val providerName: String?,
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("display_priority") val displayPriority: Int?,
)