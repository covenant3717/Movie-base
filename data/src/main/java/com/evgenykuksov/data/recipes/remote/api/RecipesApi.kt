package com.evgenykuksov.data.recipes.remote.api

import com.evgenykuksov.data.recipes.remote.model.RecipeListRemote
import retrofit2.http.GET

interface RecipesApi {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(): RecipeListRemote
}