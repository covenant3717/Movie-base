package com.evgenykuksov.data.recipes.remote.api

import com.evgenykuksov.data.recipes.remote.model.RecipeDataRemote
import retrofit2.http.GET

interface RecipesApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(): RecipeDataRemote
}