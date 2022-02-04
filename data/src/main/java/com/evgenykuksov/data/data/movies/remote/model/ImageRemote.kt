package com.evgenykuksov.data.data.movies.remote.model

import com.google.gson.annotations.SerializedName

internal data class ImageRemote(
    @SerializedName("file_path") val file_path: String?
)