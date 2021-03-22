package com.evgenykuksov.data.recipes.remote.api

import com.evgenykuksov.data.recipes.remote.model.RecipeRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RecipesApi {

    @GET("complexSearch")
    fun getRecipes(): Single<List<RecipeRemote>>
}