package com.evgenykuksov.data.data.recipes.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("imageType") val imageType: String?
)
