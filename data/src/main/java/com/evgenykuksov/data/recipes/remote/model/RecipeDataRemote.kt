package com.evgenykuksov.data.recipes.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeDataRemote(
    @SerializedName("results") val results: List<RecipeRemote>?
)