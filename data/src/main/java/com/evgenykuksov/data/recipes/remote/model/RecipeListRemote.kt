package com.evgenykuksov.data.recipes.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeListRemote(
    @SerializedName("results") val results: List<RecipeRemote>?
)
